package onlineshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import onlineshop.models.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{
	

	@Query("SELECT i FROM Item i WHERE i.shopping.id = :shoppingId ")
	List<Item> findByIdShopping(Integer shoppingId);
	
}
