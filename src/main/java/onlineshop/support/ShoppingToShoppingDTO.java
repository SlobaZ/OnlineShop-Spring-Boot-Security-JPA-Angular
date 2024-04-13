package onlineshop.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import onlineshop.dto.ShoppingDTO;
import onlineshop.models.Shopping;
import onlineshop.utils.AuxiliaryClass;


@Component
public class ShoppingToShoppingDTO implements Converter<Shopping, ShoppingDTO>{

	@Override
	public ShoppingDTO convert(Shopping shopping) {
		
		ShoppingDTO dto = new ShoppingDTO();
		
		dto.setId(shopping.getId());
		dto.setCode(shopping.getCode());
		dto.setTotalPrice(shopping.getTotalPrice());
		if(shopping.getDateTime()==null) {
			dto.setDateTimeT(AuxiliaryClass.EntriesPresentDateAndTimeSql());
			dto.setDateTime(AuxiliaryClass.EntriesPresentDateAndTimeString());
		}
		else {
			dto.setDateTime(shopping.getDateTime());
			dto.setDateTimeT(AuxiliaryClass.ConvertStringToSqlDateAndTime(shopping.getDateTime()));
			
		}
		dto.setUserId(shopping.getUser().getId());
		dto.setUserUsername(shopping.getUser().getUsername());

		return dto;
	}

	public List<ShoppingDTO> convert(List<Shopping> shoppings){
		List<ShoppingDTO> ret = new ArrayList<>();
		
		for(Shopping shopping : shoppings){
			ret.add(convert(shopping));
		}
		
		return ret;
	}

}
