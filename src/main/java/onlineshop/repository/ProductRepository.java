package onlineshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import onlineshop.models.Category;
import onlineshop.models.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	Product findByNameAndBrand(String name, String brand);
	
	@Query("SELECT p FROM Product p WHERE "
			+ "(:name IS NULL or p.name like :name ) AND "
			+ "(:brand IS NULL or p.brand like :brand) AND "
			+ "(:category IS NULL or p.category = :category) AND "
			+ "(:price IS NULL or p.price = :price) "
			)
	Page<Product> search(
			@Param("name") String name, 
			@Param("brand") String brand,
			@Param("category") Category category,
			@Param("price") Double price,
			Pageable pageRequest);
	
	


}
