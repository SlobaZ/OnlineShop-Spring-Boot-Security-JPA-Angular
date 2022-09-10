package onlineshop.controllers;

import java.util.List;


import javax.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import onlineshop.models.Shopping;
import onlineshop.models.Product;
import onlineshop.models.Category;
import onlineshop.models.Item;
import onlineshop.service.ShoppingService;
import onlineshop.service.ProductService;
import onlineshop.service.ItemService;
import onlineshop.support.ProductDTOToProduct;
import onlineshop.support.ProductToProductDTO;
import onlineshop.utils.AssignCategory;
import onlineshop.dto.ProductDTO;


@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping(value="/products")
public class ApiProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductToProductDTO toDTO;
	
	@Autowired
	private ProductDTOToProduct toProduct;
	
	@Autowired
	private ShoppingService shoppingService;
	
	@Autowired
	private ItemService itemService;
	
	
	AssignCategory ac = new AssignCategory();
	
	
	
	@GetMapping("/category")
	public Category[] getCategories(){
		return onlineshop.models.Category.values();
	}
	
		
	@GetMapping()
	ResponseEntity<List<ProductDTO>> getAllProducts(
			@RequestParam (required = false) String name,
			@RequestParam (required = false) String brand,
			@RequestParam (required = false) Category category,
			@RequestParam (required = false) Double price,
			@RequestParam(value="pageNum", defaultValue="0") int pageNum){
		
		Page<Product> productPage = null;
		
		if(name != null || brand != null || category!=null || price != null) {
			productPage = productService.search(name, brand, category, price, pageNum);
		}
		else {
			productPage = productService.findAll(pageNum);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(productPage.getTotalPages()) );
		
		return new ResponseEntity<>( toDTO.convert(productPage.getContent()) , headers , HttpStatus.OK);
	}

	
	
	
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id){
		Product product = productService.getById(id);
		if(product==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>( toDTO.convert(product), HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	ResponseEntity<ProductDTO> deleteProduct(@PathVariable Integer id){
		Product deleted = productService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>( toDTO.convert(deleted), HttpStatus.OK);
	}
	
	
	@PostMapping(consumes = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO newProductDTO ){
		
		if(newProductDTO==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Product savedProduct = productService.save(toProduct.convert(newProductDTO));
		ac.assign(savedProduct);
		List<Shopping> shoppings = shoppingService.findAll();
		for( Shopping shopping : shoppings ) {
			Item item = new Item();
			item.setProduct(savedProduct);
			itemService.save(item);
			shopping.addItem(item);
			shoppingService.save(shopping);
		}
		return new ResponseEntity<>( toDTO.convert(savedProduct), HttpStatus.CREATED);
	}
	
	
	
	@PutMapping(value="/{id}" , consumes = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	ResponseEntity<ProductDTO> updateProduct( @PathVariable Integer id, @Valid @RequestBody ProductDTO productDTO){
				
		Product persisted = productService.getById(id);
		persisted.setName(productDTO.getName());
		persisted.setBrand(productDTO.getBrand());
		persisted.setQuantity(productDTO.getQuantity());
		persisted.setPrice(productDTO.getPrice());
		persisted.setPhoto(productDTO.getPhoto());
		ac.assign(persisted);
		
		
		productService.save(persisted);
		
		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}
	
	
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	

	
	
}
