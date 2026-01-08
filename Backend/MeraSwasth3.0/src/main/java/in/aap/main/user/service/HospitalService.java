package in.aap.main.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.aap.main.jwt.JWTService;
import in.aap.main.user.dto.create.CreateHospital;
import in.aap.main.user.dto.fetch.HospitalDTO;
import in.aap.main.user.entity.Hospital;
import in.aap.main.user.entity.Users;
import in.aap.main.user.repository.HospitalRepo;
import in.aap.main.user.repository.UsersRepo;

@Service
public class HospitalService implements UserDetailsService{

	@Autowired
	private HospitalRepo repo;
	@Autowired
	private JWTService jwtservice;
	@Autowired
	private UsersRepo userrepo;
	
	private HospitalDTO maptodto(Hospital saved) {
		return new HospitalDTO(
	            saved.getId(),
	            saved.getUser(),
	            saved.getName(),
	            saved.getEmail(),
	            saved.getContact(),
	            saved.getRegistration(),
	            saved.getAccredation(),
	            saved.getAddress()
	        );
	}

	private Hospital maptoentity(CreateHospital dto) {
		Hospital hospital = new Hospital();
        hospital.setName(dto.name());
        hospital.setEmail(dto.email());
        hospital.setContact(dto.contact());
        hospital.setRegistration(dto.registration());
        hospital.setAccredation(dto.accredation());
        hospital.setAddress(dto.address());
        hospital.setPassword(jwtservice.encodePasword(dto.password()));
        return hospital;
	}
	
	public HospitalDTO create(CreateHospital dto) {
		if (dto!=null && !repo.existsByAccredation(dto.accredation())&& !repo.existsByRegistration(dto.registration())) {
			Hospital saved = repo.save(maptoentity(dto));
			userrepo.save(new Users(dto.email(),jwtservice.encodePasword(dto.password()),dto.name()));
			return maptodto(saved);
		} else {
			return null;
		}
	}
	
	public HospitalDTO get(HospitalDTO ch) {
		Hospital hosp = repo.findByUser(ch.users().getUserid());
		if (hosp!=null) {
			return maptodto(hosp);
		} else {
			return null;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Hospital hospital = repo.findByUser(Long.parseLong(username));
		if (hospital!=null) {
			return new Hospital(hospital);
		} else {
			return null;
		}
	}
	
}
