package onlineshop;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import onlineshop.models.Role;
import onlineshop.models.ERole;
import onlineshop.models.User;
import onlineshop.repository.RoleRepository;
import onlineshop.service.ShoppingService;
import onlineshop.service.UserService;
import onlineshop.service.ProductService;
import onlineshop.service.ItemService;
import onlineshop.utils.AssignCategory;
import onlineshop.models.Shopping;
import onlineshop.models.Product;
import onlineshop.models.Item;




@Component
public class TestData {
	
	@Autowired
	private UserService userService;
		
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ShoppingService shoppingService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	PasswordEncoder encoder;
	

	
	@PostConstruct
	public void init() {
		
		AssignCategory ac = new AssignCategory();
				
		Role role1 = new Role();
		role1.setName(ERole.ROLE_ADMIN);
		role1 = roleRepository.save(role1);
		
		Role role2 = new Role();
		role2.setName(ERole.ROLE_USER);
		role2 = roleRepository.save(role2);
		
		
		User user1 = new User();
		user1.setUsername("Admin");
		user1.setEmail("admin@gmail.com");
		user1.setPassword(encoder.encode("administrator"));
		user1 = userService.save(user1);
		
		User user2 = new User();
		user2.setUsername("PeraPeric");
		user2.setEmail("pera@gmail.com");
		user2.setPassword(encoder.encode("123456"));
		user2 = userService.save(user2);
		

		User user3 = new User();
		user3.setUsername("VasaVasic");
		user3.setEmail("vasa@gmail.com");
		user3.setPassword(encoder.encode("654321"));
		user3 = userService.save(user3);
		
	
		user1.addRole(role1);
		user1.addRole(role2);
		user2.addRole(role2);
		user3.addRole(role2);
		userService.save(user1);
		userService.save(user2);
		userService.save(user3);
		
		Product product1 = new Product();
		product1.setName("Television");
		product1.setBrand("Sony");
		product1.setQuantity(50);
		product1.setPrice(25000.0);
		product1.setPhoto("PictureTV.jpg");
		product1 = productService.save(product1);
		
		Product product2 = new Product();
		product2.setName("Phone");
		product2.setBrand("Samsung");
		product2.setQuantity(70);
		product2.setPrice(15000.0);
		product2.setPhoto("PicturePhone.jpg");
		product2 = productService.save(product2);
		
		Product product3 = new Product();
		product3.setName("Fridge");
		product3.setBrand("Gorenje");
		product3.setQuantity(30);
		product3.setPrice(35000.0);
		product3.setPhoto("PictureFridge.jpg");
		product3 = productService.save(product3);
		
		Product product4 = new Product();
		product4.setName("Washing machine");
		product4.setBrand("Beko");
		product4.setQuantity(40);
		product4.setPrice(30000.0);
		product4.setPhoto("PictureWashingmachine.jpg");
		product4 = productService.save(product4);
		
		Product product5 = new Product();
		product5.setName("Vacuum cleaner");
		product5.setBrand("Vox");
		product5.setQuantity(35);
		product5.setPrice(7000.0);
		product5.setPhoto("PictureVacuumcleaner.jpg");
		product5 = productService.save(product5);
		
		Product product6 = new Product();
		product6.setName("Iron");
		product6.setBrand("Gorenje");
		product6.setQuantity(60);
		product6.setPrice(4000.0);
		product6.setPhoto("PictureIron.jpg");
		product6 = productService.save(product6);
		
		Product product7 = new Product();
		product7.setName("Cooker");
		product7.setBrand("Alfa Plam");
		product7.setQuantity(25);
		product7.setPrice(30000.0);
		product7.setPhoto("PictureCooker.jpg");
		product7 = productService.save(product7);
		
		ac.assign(product1);
		ac.assign(product2);
		ac.assign(product3);
		ac.assign(product4);
		ac.assign(product5);
		ac.assign(product6);
		ac.assign(product7);
		productService.save(product1);
		productService.save(product2);
		productService.save(product3);
		productService.save(product4);
		productService.save(product5);
		productService.save(product6);
		productService.save(product7);
		
		
		Shopping shopping1 = new Shopping();
		shopping1.setCode("A 123");
		shopping1.setDateTimeT(java.sql.Timestamp.valueOf("2024-02-17 10:10:10"));
		shopping1.setDateTime("17.02.2024. 10:10");
		shopping1.setTotalPrice(194000.0);
		shopping1.setUser(user2);
		shopping1 = shoppingService.save(shopping1);
		
		Shopping shopping2 = new Shopping();
		shopping2.setCode("B 456");
		shopping2.setDateTimeT(java.sql.Timestamp.valueOf("2024-03-15 08:25:20"));
		shopping2.setDateTime("15.03.2024. 08:25");
		shopping2.setTotalPrice(96000.0);
		shopping2.setUser(user3);
		shopping2 = shoppingService.save(shopping2);
		

		Item item1 = new Item();
		item1.setItemQuantity(2);
		item1.setItemPrice(50000.0);
		item1.setProduct(product1);
		item1 = itemService.save(item1);
		
		Item item2 = new Item();
		item2.setItemQuantity(3);
		item2.setItemPrice(45000.0);
		item2.setProduct(product2);
		item2 = itemService.save(item2);
		
		Item item3 = new Item();
		item3.setItemQuantity(1);
		item3.setItemPrice(35000.0);
		item3.setProduct(product3);
		item3 = itemService.save(item3);
		
		Item item4 = new Item();
		item4.setItemQuantity(0);
		item4.setItemPrice(0.0);
		item4.setProduct(product4);
		item4 = itemService.save(item4);

		Item item5 = new Item();
		item5.setItemQuantity(0);
		item5.setItemPrice(0.0);
		item5.setProduct(product5);
		item5 = itemService.save(item5);
		
		Item item6 = new Item();
		item6.setItemQuantity(1);
		item6.setItemPrice(4000.0);
		item6.setProduct(product6);
		item6 = itemService.save(item6);
		
		Item item7 = new Item();
		item7.setItemQuantity(2);
		item7.setItemPrice(60000.0);
		item7.setProduct(product7);
		item7 = itemService.save(item7);
		
		shopping1.addItem(item1);
		shopping1.addItem(item2);
		shopping1.addItem(item3);
		shopping1.addItem(item4);
		shopping1.addItem(item5);
		shopping1.addItem(item6);
		shopping1.addItem(item7);
		shoppingService.save(shopping1);
		
		
		Item item8 = new Item();
		item8.setItemQuantity(1);
		item8.setItemPrice(25000.0);
		item8.setProduct(product1);
		item8 = itemService.save(item8);
		
		Item item9 = new Item();
		item9.setItemQuantity(1);
		item9.setItemPrice(15000.0);
		item9.setProduct(product2);
		item9 = itemService.save(item9);
		
		Item item10 = new Item();
		item10.setItemQuantity(1);
		item10.setItemPrice(45000.0);
		item10.setProduct(product3);
		item10 = itemService.save(item10);
		
		Item item11 = new Item();
		item11.setItemQuantity(0);
		item11.setItemPrice(0.0);
		item11.setProduct(product4);
		item11 = itemService.save(item11);

		Item item12 = new Item();
		item12.setItemQuantity(1);
		item12.setItemPrice(7000.0);
		item12.setProduct(product5);
		item12 = itemService.save(item12);
		
		Item item13 = new Item();
		item13.setItemQuantity(1);
		item13.setItemPrice(4000.0);
		item13.setProduct(product6);
		item13 = itemService.save(item13);
		
		Item item14 = new Item();
		item14.setItemQuantity(0);
		item14.setItemPrice(0.0);
		item14.setProduct(product7);
		item14 = itemService.save(item14);
		
		
		shopping2.addItem(item8);
		shopping2.addItem(item9);
		shopping2.addItem(item10);
		shopping2.addItem(item11);
		shopping2.addItem(item12);
		shopping2.addItem(item13);
		shopping2.addItem(item14);
		shoppingService.save(shopping2);
		
		


		

	}

}
