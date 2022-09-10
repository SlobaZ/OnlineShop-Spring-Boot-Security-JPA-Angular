package onlineshop.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import onlineshop.models.Item;
import onlineshop.models.Shopping;
import onlineshop.repository.ItemRepository;
import onlineshop.repository.ShoppingRepository;
import onlineshop.service.ShoppingService;
import onlineshop.utils.AuxiliaryClass;



@Service
public class JpaShoppingService implements ShoppingService{
	
	@Autowired
	private ShoppingRepository shoppingRepository; 
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Override
	public Shopping getById(Integer id) {
		return shoppingRepository.getById(id);
	}

	@Override
	public List<Shopping> findAll() {
		return shoppingRepository.findAll();
	}
	
	@Override
	public Page<Shopping> findAll(int pageNum) {
		PageRequest pageable = PageRequest.of(pageNum, 20);
		return shoppingRepository.findAll(pageable);
	}

	@Override
	public Shopping save(Shopping shopping) {
		return shoppingRepository.save(shopping);
	}

	@Override
	public Shopping delete(Integer id) {
		Shopping shopping = shoppingRepository.getById(id);
		if(shopping != null) {
			shoppingRepository.delete(shopping);
		}
		return shopping;
	}

	
	@Override
	public Page<Shopping> search(Long userId, String code, Double totalPrice, 
			String dateTimeBeginning, String dateTimeEnd, int pageNum) {
		
		Timestamp beginDateTime = null;
		Timestamp endDateTime = null;
		
		if( code != null) {
			code = '%' + code + '%';
		}

		if(dateTimeBeginning != null) { 
			beginDateTime = AuxiliaryClass.ConvertStringToSqlDateAndTime(dateTimeBeginning);
		}
		if(dateTimeEnd !=null) {
			 endDateTime = AuxiliaryClass.ConvertStringToSqlDateAndTime(dateTimeEnd);
		}
	
		PageRequest pageable = PageRequest.of(pageNum, 20);
		return shoppingRepository.search(userId, code, totalPrice, beginDateTime, endDateTime,  pageable);
	}

	
	@Override
	public Shopping buy(Integer id) {
		Shopping shopping = shoppingRepository.getById(id);
		List<Item> items = itemRepository.findByIdShopping(id);
		Double	x = 0.0 ;
		for (Item item: items) {
			x += item.getItemPrice();
		  }
		shopping.setTotalPrice(x);
		shoppingRepository.save(shopping);
		return shopping;
	}




}
