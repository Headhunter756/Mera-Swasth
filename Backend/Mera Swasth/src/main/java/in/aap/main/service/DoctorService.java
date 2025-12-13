package in.aap.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.aap.main.beans.Doctor;
import in.aap.main.repo.DoctorRepo;

@Service
public class DoctorService implements UserDetailsService{

	@Autowired
	private DoctorRepo repo;
	@Autowired
	private JWTService jwtservice;
	
	public Doctor create(Doctor doc) {
		if (doc!=null && repo.existsByCouncil(doc.getCouncil()) && repo.existsByLicence(doc.getLicence())) {
			doc.setPassword(jwtservice.encodePasword(doc.getPassword()));
			return repo.save(doc);
		} else {
			return null;
		}
	}
	
//	public Doctor get(String email,String password) {
//		Doctor doc = repo.findByEmail(email);
//		if(doc!=null && jwtservice.matchPassword(password, doc.getPassword())) {
//			return doc;
//		}
//		else {
//			return null;
//		}
//	}

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
