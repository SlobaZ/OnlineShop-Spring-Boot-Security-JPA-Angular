package onlineshop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import onlineshop.models.Category;
import onlineshop.models.Product;


public interface ProductService {

	Product getById(Integer id);
	List<Product> findAll();
	Page<Product> findAll(int pageNum);
	Product save(Product product);
	List<Product> saveAll(List<Product> product);
	Product delete(Integer id);
	
	Product findByNameAndBrand(String name, String brand);
	
	Page<Product> search(
			@Param("name") String name, 
			@Param("brand") String brand,
			@Param("category") Category category,
			@Param("price") Double price,
			int pageNum );
	
	

}
