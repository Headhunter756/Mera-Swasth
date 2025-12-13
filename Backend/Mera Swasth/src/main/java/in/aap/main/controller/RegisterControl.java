package in.aap.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.aap.main.beans.Doctor;
import in.aap.main.beans.Hospital;
import in.aap.main.beans.Patient;
import in.aap.main.service.DoctorService;
import in.aap.main.service.HospitalService;
import in.aap.main.service.PatientService;

@RestController
@RequestMapping("/auth/register")
public class RegisterControl {
	
	@Autowired
	private PatientService patientService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private HospitalService hopitalService;
	
	@PostMapping("/patient")
	public ResponseEntity<?> patientRegister(@RequestBody Patient patient){
		Patient p = patientService.create(patient);
		if (p!=null) {
			return ResponseEntity.accepted().build();
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();			
		}
	}
	
	@PostMapping("/doctor")
	public ResponseEntity<?> doctorRegister(@RequestBody Doctor doc){
		Doctor d = doctorService.create(doc);
		if (d!=null) {
			return ResponseEntity.accepted().build();
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();			
		}
	}
	
	@PostMapping("/hospital")
	public ResponseEntity<?> hospitalRegister(@RequestBody Hospital hospital){
		Hospital h = hopitalService.create(hospital);
		if (h!=null) {
			return ResponseEntity.accepted().build();
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();			
		}
	}
	
}
