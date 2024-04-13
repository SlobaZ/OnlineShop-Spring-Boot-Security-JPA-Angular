package onlineshop.support;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import onlineshop.dto.ProductDTO;
import onlineshop.models.Product;
import onlineshop.service.ProductService;




@Component
public class ProductDTOToProduct implements Converter<ProductDTO, Product> {
	
	@Autowired
	private ProductService productService;

	@Override
	public Product convert(ProductDTO dto) {
		
		Product product = new Product();
				
		if(dto.getId()!=null){
			
			product = productService.getReferenceById(dto.getId());
			
			if(product == null){
				throw new IllegalStateException("Tried to modify a non-existant Product");
			}
		}

		product.setId(dto.getId());
		product.setName(dto.getName());
		product.setBrand(dto.getBrand());
		product.setQuantity(dto.getQuantity());
		product.setPrice(dto.getPrice());
		product.setPhoto(dto.getPhoto());
		
		if(dto.getCategory()!=null) {
			product.setCategory(dto.getCategory());
		}
		
		return product;
	}
	
	public List<Product> convert (List<ProductDTO> dtoProducts){
		List<Product> products = new ArrayList<>();
		
		for(ProductDTO dto : dtoProducts){
			products.add(convert(dto));
		}
		
		return products;
	}

}
