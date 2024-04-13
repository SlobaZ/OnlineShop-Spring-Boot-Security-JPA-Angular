package onlineshop.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import onlineshop.dto.ItemDTO;
import onlineshop.models.Item;



@Component
public class ItemToItemDTO implements Converter<Item, ItemDTO> {

	@Override
	public ItemDTO convert(Item item) {
		if(item==null){
			return null;
		}
		
		ItemDTO dto = new ItemDTO();
			
		dto.setId(item.getId());
		dto.setItemQuantity(item.getItemQuantity());
		dto.setItemPrice(item.getItemPrice());
		
		dto.setShoppingId(item.getShopping().getId());
		dto.setShoppingCode(item.getShopping().getCode());
		dto.setShoppingTotalPrice(item.getShopping().getTotalPrice());
		dto.setShoppingDateTimeT(item.getShopping().getDateTimeT());
		dto.setShoppingDateTime(item.getShopping().getDateTime());
		
		dto.setProductId(item.getProduct().getId());
		dto.setProductName(item.getProduct().getName());
		dto.setProductQuantity(item.getProduct().getQuantity());
		dto.setProductPrice(item.getProduct().getPrice());
		
		return dto;
	}
	
	public List<ItemDTO> convert(List<Item> items){
		List<ItemDTO> ret = new ArrayList<>();
		
		for(Item i: items){
			ret.add(convert(i));
		}
		
		return ret;
	}

}
