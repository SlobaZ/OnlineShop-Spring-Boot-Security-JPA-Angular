package onlineshop.support;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import onlineshop.dto.ShoppingDTO;
import onlineshop.models.User;
import onlineshop.models.Shopping;

import onlineshop.service.ShoppingService;
import onlineshop.service.UserService;
import onlineshop.utils.AuxiliaryClass;


@Component
public class ShoppingDTOToShopping implements Converter<ShoppingDTO, Shopping>{

	@Autowired
	private ShoppingService shoppingService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Shopping convert(ShoppingDTO shoppingDTO) {
		
		User user = userService.getReferenceById(shoppingDTO.getUserId());
		if(user!=null) {
		Shopping shopping = null;
		
		if(shoppingDTO.getId() != null){
			shopping = shoppingService.getReferenceById(shoppingDTO.getId());

		}
		else {
			shopping = new Shopping();
		}
					
			shopping.setId(shoppingDTO.getId());
			shopping.setCode(shoppingDTO.getCode());
			shopping.setTotalPrice(shoppingDTO.getTotalPrice());
			if(shoppingDTO.getDateTime()==null) {
				shopping.setDateTime(AuxiliaryClass.ConvertSqlDateAndTimeToString(AuxiliaryClass.EntriesPresentDateAndTimeSql()));
				shopping.setDateTimeT(AuxiliaryClass.EntriesPresentDateAndTimeSql());
			}
			if(shoppingDTO.getDateTime()!=null) {
				shopping.setDateTime(shoppingDTO.getDateTime());
				shopping.setDateTimeT(AuxiliaryClass.ConvertStringToSqlDateAndTime(shoppingDTO.getDateTime()));
			}
			shopping.setUser(user);
			return shopping;
		}
		else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
	}

	

	public List<Shopping> convert(List<ShoppingDTO> shoppingDTOs){
		List<Shopping> ret = new ArrayList<>();
		
		for(ShoppingDTO shoppingDTO : shoppingDTOs){
			ret.add(convert(shoppingDTO));
		}
		
		return ret;
	}
}
