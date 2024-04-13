package onlineshop.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import onlineshop.dto.ItemDTO;
import onlineshop.models.Shopping;
import onlineshop.models.Product;
import onlineshop.models.Item;
import onlineshop.service.ShoppingService;
import onlineshop.service.ProductService;
import onlineshop.service.ItemService;


@Component
public class ItemDTOToItem implements Converter<ItemDTO, Item> {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ShoppingService shoppingService;
	
	@Autowired
	private ProductService productService;

	@Override
	public Item convert(ItemDTO dto) {
		
		Shopping shopping = shoppingService.getReferenceById(dto.getShoppingId());
		Product product = productService.getReferenceById(dto.getProductId());
		
		if(shopping!=null && product!=null) {
			
			Item item = null;
			
			if(dto.getId() != null) {
				item = itemService.getReferenceById(dto.getId());
			}
			else {
				item = new Item();
			}
			
			item.setId(dto.getId());
			item.setItemQuantity(dto.getItemQuantity());
			item.setItemPrice(dto.getItemPrice());
			
			item.setShopping(shopping);
			item.setProduct(product);
			
			return item;
		}
		else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
		
		
	
	}
	
	public List<Item> convert (List<ItemDTO> dtoItems){
		List<Item> items = new ArrayList<>();
		
		for(ItemDTO dto : dtoItems){
			items.add(convert(dto));
		}
		
		return items;
	}

}
