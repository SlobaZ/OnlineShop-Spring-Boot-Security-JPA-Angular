package onlineshop.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import onlineshop.models.Shopping;
import onlineshop.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);
	
	@Query("SELECT u FROM User u WHERE u.username = :username")
	User findbyUsername(String username);
	
	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE "
			+ "(:username IS NULL or u.username like :username ) AND "
			+ "(:email IS NULL OR u.email like :email) "
			)
	Page<User> search(
			@Param("username") String username, 
			@Param("email") String email,
			Pageable pageRequest);
	
	
	
	@Query("SELECT s FROM Shopping s WHERE s.user.id = :idU")
	Shopping dataOfUser( @Param("idU") Integer idU);
	
		
}
