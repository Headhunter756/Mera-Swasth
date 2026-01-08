package in.aap.main.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.aap.main.users.dto.DoctorDTO;
import in.aap.main.users.dto.HospitalDTO;
import in.aap.main.users.dto.PatientDTO;
import in.aap.main.users.dto.create.CreateDoctorDTO;
import in.aap.main.users.dto.create.CreateHospital;
import in.aap.main.users.dto.create.CreatePatient;
import in.aap.main.users.service.DoctorService;
import in.aap.main.users.service.HospitalService;
import in.aap.main.users.service.PatientService;

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
	public ResponseEntity<?> patientRegister(@RequestBody CreatePatient patient){
		PatientDTO p = patientService.create(patient);
		if (p!=null) {
			return ResponseEntity.accepted().build();
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();			
		}
	}
	
	@PostMapping("/doctor")
	public ResponseEntity<?> doctorRegister(@RequestBody CreateDoctorDTO doc){
		DoctorDTO d = doctorService.create(doc);
		if (d!=null) {
			return ResponseEntity.accepted().build();
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();			
		}
	}
	
	@PostMapping("/hospital")
	public ResponseEntity<?> hospitalRegister(@RequestBody CreateHospital hospital){
		HospitalDTO h = hopitalService.create(hospital);
		if (h!=null) {
			return ResponseEntity.accepted().build();
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();			
		}
	}
	
}
