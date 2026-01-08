package in.aap.main.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.aap.main.user.entity.Patient;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long>{
	boolean existsByAadhaar(int aadhaar);
	Patient findByEmail(String email);
	Patient findByUser(long userid);
}
