package in.aap.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.aap.main.beans.Patient;
import in.aap.main.repo.PatientRepo;

@Service
public class PatientService implements UserDetailsService{

	@Autowired
	private PatientRepo repo;
	@Autowired
	private JWTService jwtservice;
	
	public Patient create(Patient patient) {
		if(patient!=null && !repo.existsByAadhaar(patient.getAadhaar())) {
			patient.setPassword(jwtservice.encodePasword(patient.getPassword()));
			Patient p = repo.save(patient);
			return p;
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
