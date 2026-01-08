package in.aap.main.users.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.aap.main.users.dto.DoctorDTO;
import in.aap.main.users.dto.create.CreateDoctorDTO;
import in.aap.main.users.entity.Doctor;
import in.aap.main.users.repository.DoctorRepo;

@Service
public class DoctorService implements UserDetailsService{

	@Autowired
	private DoctorRepo repo;
	@Autowired
	private JWTService jwtservice;
	
	public DoctorDTO create(CreateDoctorDTO doc) {
		if (doc!=null && repo.existsByCouncil(doc.council()) && repo.existsByLicence(doc.licence())) {
			
			Doctor doctor = new Doctor();
			doctor.setName(doc.name());
			doctor.setPassword(jwtservice.encodePasword(doc.password()));
			doctor.setContact(doc.contact());
			doctor.setEmail(doc.email());
			doctor.setCouncil(doc.council());
			doctor.setSpecialization(doc.specialization());
			doctor.setLicence(doc.licence());
			doctor.setAddress(doc.address());
			
			Doctor saved = repo.save(doctor);
			
			return new DoctorDTO(
					saved.getId(),
					saved.getName(),
					saved.getEmail(),
					saved.getContact(),
					saved.getCouncil(),
					saved.getLicence(),
					saved.getSpecialization(),
					saved.getAddress()
					);
		} else {
			return null;
		}
	}
	
	public Doctor get(String email,String password) {
		Doctor doc = repo.findByEmail(email);
		if(doc!=null && jwtservice.matchPassword(password, doc.getPassword())) {
			return doc;
		}
		else {
			return null;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Doctor doc = repo.findByEmail(username);
		if (doc!=null) {
			return new Doctor(doc);
		} else {
			return null;
		}
	}
	
}
