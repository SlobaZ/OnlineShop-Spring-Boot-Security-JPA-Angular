package onlineshop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import onlineshop.models.Shopping;



public interface ShoppingService {
	
	Shopping getReferenceById(Integer id);
	List<Shopping> findAll();
	Page<Shopping> findAll(int pageNum);
	Shopping save(Shopping shopping);
	Shopping delete(Integer id);
		
	Page<Shopping> search(
			@Param("userid") Integer userId, 
			@Param("code") String code, 
			@Param("totalPrice") Double totalPrice,
			@Param("dateTimeBeginning") String dateTimeBeginning,
			@Param("dateTimeEnd") String dateTimeEnd,
			 int pageNum);

	
	Shopping createShopping();
	Shopping buy(Integer id);


}
