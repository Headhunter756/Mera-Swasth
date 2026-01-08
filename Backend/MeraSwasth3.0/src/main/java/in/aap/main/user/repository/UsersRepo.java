package in.aap.main.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.aap.main.user.entity.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long>{

	Users findByEmail(String email);
}
