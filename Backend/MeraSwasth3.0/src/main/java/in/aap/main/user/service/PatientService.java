package in.aap.main.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.aap.main.jwt.JWTService;
import in.aap.main.user.dto.create.CreatePatient;
import in.aap.main.user.dto.fetch.PatientDTO;
import in.aap.main.user.entity.Patient;
import in.aap.main.user.entity.Users;
import in.aap.main.user.repository.PatientRepo;
import in.aap.main.user.repository.UsersRepo;

@Service
public class PatientService implements UserDetailsService{

	@Autowired
	private PatientRepo repo;
	@Autowired
	private JWTService jwtservice;
	@Autowired
	private UsersRepo userrepo;
	
	private PatientDTO maptodto(Patient saved) {
		return new PatientDTO(
				saved.getId(),
				saved.getUser(),
				saved.getName(), 
				saved.getEmail(), 
				saved.getContact(), 
				saved.getAadhaar(), 
				saved.getAddress(), 
				saved.getAbha()
				);
		}
	
	private Patient maptoentity(CreatePatient patient) {
		Patient pat = new Patient();
		pat.setName(patient.name());
		pat.setEmail(patient.email());
		pat.setPassword(jwtservice.encodePasword(patient.password()));
		pat.setContact(patient.contact());
		pat.setAadhaar(patient.aadhaar());
		pat.setAddress(patient.address());
		pat.setAbha(patient.abha());
		return pat;
	}
	
	public PatientDTO create(CreatePatient patient) {
		if(patient!=null && !repo.existsByAadhaar(patient.aadhaar())) {
			Patient saved = repo.save(maptoentity(patient));
			userrepo.save(new Users(patient.email(),jwtservice.encodePasword(patient.password()),patient.name()));
			return maptodto(saved);
		}
		else {			
			return null;
		}
	}
	
	public PatientDTO get(PatientDTO pd) {
		Patient patient = repo.findByUser(pd.user().getUserid());
		if (patient!=null) {
			return maptodto(patient);
		} else {
			//System.out.println("Password didn't match");
			return null;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Patient patient = repo.findByUser(Long.parseLong(username));
		if (patient!=null) {
			return new Patient(patient);
		} else {
			return null;			
		}
	}
	
}
