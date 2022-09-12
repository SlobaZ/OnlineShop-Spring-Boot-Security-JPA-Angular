package onlineshop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import onlineshop.models.Shopping;



public interface ShoppingService {
	
	Shopping getById(Integer id);
	List<Shopping> findAll();
	Page<Shopping> findAll(int pageNum);
	Shopping save(Shopping shopping);
	Shopping delete(Integer id);
		
	Page<Shopping> search(
			@Param("userid") Long userId, 
			@Param("code") String code, 
			@Param("totalPrice") Double totalPrice,
			@Param("dateTimeBeginning") String dateTimeBeginning,
			@Param("dateTimeEnd") String dateTimeEnd,
			 int pageNum);

	
	Shopping buy(Integer id);


}
