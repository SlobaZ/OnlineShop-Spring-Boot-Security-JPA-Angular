package onlineshop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import onlineshop.models.Item;

public interface ItemService {
	
	Item getReferenceById(Integer id);
	List<Item> findAll();
	Page<Item> findAll(int pageNum);
	Item save(Item item);
	Item delete(Integer id);
			
	List<Item> findByIdShopping(Integer shoppingId);
	
	Item buyItem (Integer id , int quantityItem);
	
	Item resetItem (Integer id);
}
