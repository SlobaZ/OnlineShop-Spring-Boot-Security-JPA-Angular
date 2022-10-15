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
		
		ShoppingDTO retValue = new ShoppingDTO();
		
		retValue.setId(shopping.getId());
		retValue.setCode(shopping.getCode());
		retValue.setTotalPrice(shopping.getTotalPrice());
		if(shopping.getDateTime()==null) {
			retValue.setDateTimeT(AuxiliaryClass.EntriesPresentDateAndTimeSql());
			retValue.setDateTime(AuxiliaryClass.ViewsTextualDateTime(AuxiliaryClass.EntriesPresentDateAndTimeSql()));
		}
		if(shopping.getDateTime()!=null)  {
			retValue.setDateTimeT(shopping.getDateTimeT());
			retValue.setDateTime(shopping.getDateTime());       // AuxiliaryClass.ViewsTextualDateTime(shopping.getDateTimeT())
		}
		
		retValue.setUserId(shopping.getUser().getId());
		retValue.setUserUsername(shopping.getUser().getUsername());

		return retValue;
	}

	public List<ShoppingDTO> convert(List<Shopping> shoppings){
		List<ShoppingDTO> ret = new ArrayList<>();
		
		for(Shopping shopping : shoppings){
			ret.add(convert(shopping));
		}
		
		return ret;
	}

}
