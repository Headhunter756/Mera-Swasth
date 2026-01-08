package in.aap.main.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.aap.main.jwt.JWTService;
import in.aap.main.user.dto.create.CreateUser;
import in.aap.main.user.dto.fetch.UsersDTO;

//import in.aap.main.user.dto.create.CreateUser;
//import in.aap.main.user.dto.fetch.UsersDTO;
import in.aap.main.user.entity.Users;
import in.aap.main.user.repository.UsersRepo;

@Service
public class UsersService implements UserDetailsService{

	@Autowired
	private PatientService ps;
	@Autowired
	private DoctorService ds;
	@Autowired
	private HospitalService hs;
	@Autowired
	private UsersRepo repo;
	@Autowired
	private JWTService jwt;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//System.out.println("Reached here");
		try {
			//System.out.println("Send to main");
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
	
//	private Users maptoentity(CreateUser user) {
//		Users u = new Users();
//		u.setEmail(user.email());
//		u.setPassword(user.password());
//		return u;
//	}
	
	private UsersDTO maptodto(Users user) {
		return new UsersDTO(user.getUserid(),user.getEmail(),user.getName());
	}

//	public void create(CreateUser user) {
//		Users u = repo.save(null);
//	}
	
	public UsersDTO get(CreateUser user) {
		Users u = repo.findByEmail(user.email());
		if (u!=null && jwt.matchPassword(user.password(), u.getPassword())) {
			return maptodto(u);
		} else {
			return null;
		}
	}
}