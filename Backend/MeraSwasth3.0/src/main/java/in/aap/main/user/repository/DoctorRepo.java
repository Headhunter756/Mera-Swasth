package in.aap.main.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.aap.main.user.entity.Doctor;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long>{

	boolean existsByLicence(String licence);
	boolean existsByCouncil(String council);
	Doctor findByEmail(String email);
	Doctor findByUser(long userid);
}
