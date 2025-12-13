package in.aap.main.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.aap.main.beans.Patient;
import in.aap.main.service.JWTService;
import in.aap.main.service.PatientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("/auth")
public class LoginControl {
	
	@Autowired
	private JWTService jwtservice;
	@Autowired
	private PatientService patientService;
	Logger logger = Logger.getLogger(LoginControl.class.getName());
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Login login) {
		logger.log(Level.INFO, "Request reached at backend");
		Patient patient = patientService.get(login.getEmail(), login.getPassword());
		if (patient!=null) {
			logger.log(Level.INFO, "user found");
			boolean match = jwtservice.matchPassword(login.getPassword(), patient.getPassword());
			if (match) {
				logger.log(Level.INFO, "Password matched sending the data");
				String token = jwtservice.tokengen(patient.getUsername());
				return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token).build();
			} else {
				logger.log(Level.WARNING, "Passowrd didn't match");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		}else {
			return ResponseEntity.notFound().build();			
		}
	}
	
	@PostMapping("/logout")
	public ResponseEntity<?> logout(@RequestHeader("Authorization") String token, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if (token!=null && !token.isBlank()) {
			if (session!=null) {
				session.invalidate();				
			}
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
}

class Login{
	
	private String email;
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPasssword(String password) {
		this.password = password;
	}
	
}
