package in.aap.main.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.aap.main.jwt.JWTService;
import in.aap.main.user.dto.create.CreateDoctor;
import in.aap.main.user.dto.fetch.DoctorDTO;
import in.aap.main.user.entity.Doctor;
import in.aap.main.user.entity.Users;
import in.aap.main.user.repository.DoctorRepo;
import in.aap.main.user.repository.UsersRepo;

@Service
public class DoctorService implements UserDetailsService{

	@Autowired
	private DoctorRepo repo;
	@Autowired
	private JWTService jwtservice;
	@Autowired
	private UsersRepo userrepo;
	
	public DoctorDTO maptodto(Doctor saved) {
		return new DoctorDTO(
				saved.getId(),
				saved.getUser(),
				saved.getName(),
				saved.getEmail(),
				saved.getContact(),
				saved.getCouncil(),
				saved.getLicence(),
				saved.getSpecialization(),
				saved.getAddress()
				);
	}
	
	public Doctor maptoentity(CreateDoctor doc) {
		Doctor doctor = new Doctor();
		doctor.setName(doc.name());
		doctor.setPassword(jwtservice.encodePasword(doc.password()));
		doctor.setContact(doc.contact());
		doctor.setEmail(doc.email());
		doctor.setCouncil(doc.council());
		doctor.setSpecialization(doc.specialization());
		doctor.setLicence(doc.licence());
		doctor.setAddress(doc.address());
		return doctor;
	}
	
	public DoctorDTO create(CreateDoctor doc) {
		if (doc!=null && repo.existsByCouncil(doc.council()) && repo.existsByLicence(doc.licence())) {
			Doctor saved = repo.save(maptoentity(doc));
			userrepo.save(new Users(doc.email(),jwtservice.encodePasword(doc.password()),doc.name()));
			return maptodto(saved);
		} else {
			return null;
		}
	}
	
	public DoctorDTO get(DoctorDTO cd) {
		Doctor doc = repo.findByUser(cd.user().getUserid());
		if(doc!=null) {
			return maptodto(doc);
		}
		else {
			return null;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Doctor doc = repo.findByUser(Long.parseLong(username));
		if (doc!=null) {
			return new Doctor(doc);
		} else {
			return null;
		}
	}
	
}
