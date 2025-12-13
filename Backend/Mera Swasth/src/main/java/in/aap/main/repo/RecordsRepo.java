package in.aap.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.aap.main.beans.Records;
import java.util.List;


@Repository
public interface RecordsRepo extends JpaRepository<Records, Long>{

	List<Records> findByPatient(long id);
}
