package in.aap.main.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.aap.main.beans.Records;
import in.aap.main.service.RecordsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "/record")
public class RecordsControl {

	@Autowired
	private RecordsService recordservice;
	Logger logger = LogManager.getLogger(RecordsControl.class);
	
	@PostMapping("/getall")
	public ResponseEntity<?> getRecord(@RequestBody long id) {
		List<Records> records = recordservice.getAll(id);
		if(records.isEmpty()) {
			logger.error("Records not found for userid: "+id);
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(records);			
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addRecord(@RequestBody Records record) {
		boolean add = recordservice.add(record);
		if(add) {
			return ResponseEntity.accepted().build();
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();			
		}
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> updateRecord(@RequestBody Records record) {
		boolean update= recordservice.update(record);
		if(update) {
			return ResponseEntity.accepted().build();
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();			
		}
	}
	
	@PostMapping("/delete")
	public ResponseEntity<?> deleteRecord(@RequestBody long id) {
		//TODO: process POST request
		boolean delete = recordservice.delete(id);
		if (delete) {
			return ResponseEntity.accepted().build();			
		} else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
}