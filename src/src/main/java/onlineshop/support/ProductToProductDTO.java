package onlineshop.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import onlineshop.dto.ProductDTO;
import onlineshop.models.Product;



@Component
public class ProductToProductDTO implements Converter<Product, ProductDTO> {

	@Override
	public ProductDTO convert(Product product) {
		if(product==null){
			return null;
		}
		
		ProductDTO dto = new ProductDTO();
		
		dto.setId(product.getId());
		dto.setName(product.getName());
		dto.setBrand(product.getBrand());
		dto.setQuantity(product.getQuantity());
		dto.setPrice(product.getPrice());
		dto.setPhoto(product.getPhoto());
		
		if(product.getCategory()!=null) {
		dto.setCategory(product.getCategory());
		}
		
		return dto;
	}
	
	public List<ProductDTO> convert(List<Product> products){
		List<ProductDTO> ret = new ArrayList<>();
		
		for(Product p: products){
			ret.add(convert(p));
		}
		
		return ret;
	}

}
