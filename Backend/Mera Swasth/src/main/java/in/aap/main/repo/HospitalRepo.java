package in.aap.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.aap.main.beans.Hospital;


@Repository
public interface HospitalRepo extends JpaRepository<Hospital, Long>{

	boolean existsByRegistration(String registration);
	boolean existsByAccredation(String accredation);
	Hospital findByEmail(String email);
}
