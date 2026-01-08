package in.aap.main.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.aap.main.users.dto.HospitalDTO;
import in.aap.main.users.dto.create.CreateHospital;
import in.aap.main.users.entity.Hospital;
import in.aap.main.users.repository.HospitalRepo;

@Service
public class HospitalService implements UserDetailsService{

	@Autowired
	private HospitalRepo repo;
	@Autowired
	private JWTService jwtservice;
	
	public HospitalDTO create(CreateHospital dto) {
		Hospital hospital = new Hospital();
        hospital.setName(dto.name());
        hospital.setEmail(dto.email());
        hospital.setContact(dto.contact());
        hospital.setRegistration(dto.registration());
        hospital.setAccredation(dto.accredation());
        hospital.setAddress(dto.address());
        hospital.setPassword(jwtservice.encodePasword(dto.password()));

        Hospital saved = repo.save(hospital);

        return new HospitalDTO(
            saved.getId(),
            saved.getName(),
            saved.getEmail(),
            saved.getContact(),
            saved.getRegistration(),
            saved.getAccredation(),
            saved.getAddress()
        );

	}
	
	public Hospital get(String email,String password) {
		Hospital hosp = repo.findByEmail(email);
		if (hosp!=null && jwtservice.matchPassword(password, hosp.getPassword())) {
			return hosp;
		} else {
			return null;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Hospital hospital = repo.findByEmail(username);
		if (hospital!=null) {
			return new Hospital(hospital);
		} else {
			return null;
		}
	}
	
}
