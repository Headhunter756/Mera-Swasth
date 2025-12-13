package in.aap.main.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.aap.main.beans.Records;
import in.aap.main.repo.RecordsRepo;

@Service
public class RecordsService {
	
	@Autowired
	private RecordsRepo repo;
	
	Logger logger = LogManager.getLogger(RecordsService.class);

	public List<Records> getAll(long id) {
		List<Records> records = repo.findByPatient(id);
		if(!records.isEmpty()) {
			logger.info("Records Found");
			return records;
		}
		else {
			logger.error("Records Not Found");
			return null;			
		}
	}
	
	public boolean add(Records record) {
		Records r = repo.save(record);
		if(r!=null) {
			logger.info("Record Saved");
			return true;
		}
		else {
			logger.error("Record couldn't be added");
			return false;			
		}
	}
	
	public boolean update(Records record) {
		Records r = repo.save(record);
		if(r!=null) {
			logger.info("Record Updated");
			return true;
		}
		else {
			logger.error("Records couldn't be updated");
			return false;			
		}
	}
	
	public boolean delete(long id) {
		Optional<Records> r = repo.findById(id);
		if(r.isPresent()) {
			repo.delete(r.get());
			logger.info("Records Deleted");
			return true;
		}
		else {
			logger.error("Records couldn't be deleted");
			return false;			
		}
	}
	
	
	
}
