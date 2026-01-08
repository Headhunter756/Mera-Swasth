package in.aap.main.user.resolver;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import in.aap.main.jwt.JWTService;
import in.aap.main.user.dto.create.CreateDoctor;
import in.aap.main.user.dto.create.CreateHospital;
import in.aap.main.user.dto.create.CreatePatient;
import in.aap.main.user.dto.create.CreateUser;
import in.aap.main.user.dto.fetch.AuthResponse;
import in.aap.main.user.dto.fetch.DoctorDTO;
import in.aap.main.user.dto.fetch.HospitalDTO;
import in.aap.main.user.dto.fetch.PatientDTO;
import in.aap.main.user.dto.fetch.UsersDTO;
import in.aap.main.user.service.DoctorService;
import in.aap.main.user.service.HospitalService;
import in.aap.main.user.service.PatientService;
import in.aap.main.user.service.UsersService;

@Controller
public class UserResolver {

	@Autowired
	private DoctorService docservice;
	@Autowired
	private HospitalService hospservice;
	@Autowired
	private PatientService patservice;
	@Autowired
	private UsersService userservice;
	@Autowired
	private JWTService jwtservice;

	@MutationMapping
	public boolean createDoctor(@Argument("input") CreateDoctor cd) {
		DoctorDTO dto = docservice.create(cd);
		if (dto!=null) {
			return true;
		} else {
			return false;
		}
	}
	
	@MutationMapping
	public boolean createHospital(@Argument("input") CreateHospital ch) {
		HospitalDTO dto = hospservice.create(ch);
		if (dto!=null) {
			return true;
		} else {
			return false;
		}
	}
	
	@MutationMapping
	public boolean createPatient(@Argument("input") CreatePatient cp) {
		PatientDTO dto = patservice.create(cp);
		if (dto!=null) {
			return true;
		} else {
			return false;
		}
	}
	
	@MutationMapping
	public AuthResponse login(@Argument("input") CreateUser user){
		UsersDTO dto = userservice.get(user);
		if (dto!=null) {
			return new AuthResponse(jwtservice.tokengen(dto.userid()),dto);
		} else {
			throw new RuntimeException("Invalid Username or Password");
		}
	}
	
//	@SchemaMapping(typeName = "AuthResponse", field = "user")
//	public UsersDTO getUser(@Argument("input") CreateUser user) {
//		UsersDTO dto = userservice.get(user);
//		if (dto!=null) {
//			return dto;
//		} else {
//			return null;
//		}
//	}
//	
//	@SchemaMapping(typeName = "login",field = "token")
//	public String login(@Argument("input") CreateUser user) {
//		UsersDTO dto = userservice.get(user);
//		if (dto!=null) {
//			return jwtservice.tokengen(dto.userid());
//		} else {
//			return "";
//		}
//	}
	
	@QueryMapping
	public String hello() {
		return "Hello";
	}
}
