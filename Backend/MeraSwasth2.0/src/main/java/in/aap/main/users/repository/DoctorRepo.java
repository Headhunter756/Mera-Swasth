package in.aap.main.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.aap.main.users.entity.Doctor;


@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long>{

	boolean existsByLicence(String licence);
	boolean existsByCouncil(String council);
	Doctor findByEmail(String email);
}
