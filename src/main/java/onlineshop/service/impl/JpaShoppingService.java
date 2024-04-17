package onlineshop.service.impl;

import java.sql.Timestamp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import onlineshop.models.Item;
import onlineshop.models.Product;
import onlineshop.models.Shopping;
import onlineshop.models.User;
import onlineshop.repository.ItemRepository;
import onlineshop.repository.ProductRepository;
import onlineshop.repository.ShoppingRepository;
import onlineshop.repository.UserRepository;
import onlineshop.service.ShoppingService;
import onlineshop.utils.AuxiliaryClass;



@Service
public class JpaShoppingService implements ShoppingService{
	
	@Autowired
	private ShoppingRepository shoppingRepository; 
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	
	
	@Override
	public Shopping getReferenceById(Integer id) {
		return shoppingRepository.getReferenceById(id);
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
		Shopping shopping = shoppingRepository.getReferenceById(id);
		if(shopping != null) {
			shoppingRepository.delete(shopping);
		}
		return shopping;
	}

	
	@Override
	public Page<Shopping> search(Integer userId, String code, Double totalPrice, 
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
	public Shopping createShopping() {
		
		User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = String.valueOf ( userDetails.getUsername() );
		User user = userRepository.findbyUsername(username);

		Shopping shopping = new Shopping();
		shopping.setCode(AuxiliaryClass.AssignCode());
		shopping.setDateTimeT(AuxiliaryClass.EntriesPresentDateAndTimeSql());
		shopping.setDateTime(AuxiliaryClass.ConvertSqlDateAndTimeToString(AuxiliaryClass.EntriesPresentDateAndTimeSql()));
		shopping.setTotalPrice(0.0);
		shopping.setUser(user);
		shoppingRepository.save(shopping);
		
		List<Product> products = productRepository.findAll();
		for(Product product:products) {
			Item item = new Item();
			item.setItemQuantity(0);
			item.setItemPrice(0.0);
			item.setProduct(product);
			itemRepository.save(item);
			shopping.addItem(item);
		}
		shoppingRepository.save(shopping);
		
		return shopping;
	}

	
	@Override
	public Shopping buy(Integer id) {
		Shopping shopping = shoppingRepository.getReferenceById(id);
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
