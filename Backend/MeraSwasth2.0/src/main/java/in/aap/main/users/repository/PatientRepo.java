package in.aap.main.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.aap.main.users.entity.Patient;


@Repository
public interface PatientRepo extends JpaRepository<Patient, Long>{
	boolean existsByAadhaar(int aadhaar);
	Patient findByEmail(String email);
}
