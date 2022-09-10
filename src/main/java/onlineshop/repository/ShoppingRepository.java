package onlineshop.repository;

import java.sql.Timestamp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import onlineshop.models.Shopping;


@Repository
public interface ShoppingRepository extends JpaRepository<Shopping, Integer>{

//	Page<Shopping> findByUserId(Long userId, Pageable page);
	
	@Query("SELECT s FROM Shopping s WHERE "
			+ "(:userId IS NULL or s.user.id = :userId ) AND "
			+ "(:code IS NULL OR s.code like :code) AND "
			+ "(:totalPrice IS NULL or s.totalPrice = :totalPrice ) AND "
			+ "(:dateTimeBeginning IS NULL or s.dateTimeT >= :dateTimeBeginning ) AND "
			+ "(:dateTimeEnd IS NULL or s.dateTimeT <= :dateTimeEnd ) "
			)
	Page<Shopping> search(
			@Param("userId") Long userId, 
			@Param("code") String code, 
			@Param("totalPrice") Double totalPrice,
			@Param("dateTimeBeginning") Timestamp dateTimeBeginning,
			@Param("dateTimeEnd") Timestamp dateTimeEnd,
			Pageable pageRequest);
	
}
