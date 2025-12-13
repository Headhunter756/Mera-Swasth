package in.aap.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.aap.main.beans.Doctor;


@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long>{

	boolean existsByLicence(String licence);
	boolean existsByCouncil(String council);
	Doctor findByEmail(String email);
}
