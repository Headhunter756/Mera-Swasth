package in.aap.main.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.aap.main.users.dto.PatientDTO;
import in.aap.main.users.dto.create.CreatePatient;
import in.aap.main.users.entity.Patient;
import in.aap.main.users.repository.PatientRepo;

@Service
public class PatientService implements UserDetailsService{

	@Autowired
	private PatientRepo repo;
	@Autowired
	private JWTService jwtservice;
	
	public PatientDTO create(CreatePatient patient) {
		if(patient!=null && !repo.existsByAadhaar(patient.aadhaar())) {
			
			Patient pat = new Patient();
			pat.setName(patient.name());
			pat.setEmail(patient.email());
			pat.setPassword(jwtservice.encodePasword(patient.password()));
			pat.setContact(patient.contact());
			pat.setAadhaar(patient.aadhaar());
			pat.setAddress(patient.address());
			pat.setAbha(patient.abha());
			Patient saved = repo.save(pat);
			
			return new PatientDTO(
					saved.getId(), 
					saved.getName(), 
					saved.getEmail(), 
					saved.getContact(), 
					saved.getAadhaar(), 
					saved.getAddress(), 
					saved.getAbha()
					);
		}
		else {			
			return null;
		}
	}
	
	public Patient get(String email,String password) {
		Patient patient = repo.findByEmail(email);
		if (patient!=null) {
			return patient;
		} else {
			System.out.println("Password didn't match");
			return null;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Patient patient = repo.findByEmail(username);
		if (patient!=null) {
			return new Patient(patient);
		} else {
			return null;			
		}
	}
	
}
