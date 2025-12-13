package in.aap.main.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDertailsService implements UserDetailsService{

	private PatientService ps;
	private DoctorService ds;
	private HospitalService hs;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Reached here");
		try {
			System.out.println("Send to main");
			return ps.loadUserByUsername(username);
		} catch (Exception e) {}
		
		try {
			return ds.loadUserByUsername(username);
		} catch (Exception e) {}
		
		try {
			return hs.loadUserByUsername(username);
		} catch (Exception e) {}
		
		throw new UsernameNotFoundException("No user Found with email: "+username);
	}
}