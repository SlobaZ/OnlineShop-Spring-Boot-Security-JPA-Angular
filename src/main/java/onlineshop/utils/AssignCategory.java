package onlineshop.utils;

import onlineshop.models.Category;
import onlineshop.models.Product;

public class AssignCategory {
	
	public Product assign(Product product) {
		
		if(product.getName().equalsIgnoreCase("Television") || product.getName().equalsIgnoreCase("TV")) {
			product.setCategory(Category.VIDEO);
		}
		else if(product.getName().equalsIgnoreCase("Phone") || product.getName().equalsIgnoreCase("Mobile Phone")) {
			product.setCategory(Category.PHONES);
		}
		else if(product.getName().equalsIgnoreCase("Fridge") || product.getName().equalsIgnoreCase("Freezer")) {
			product.setCategory(Category.WHITE_GOODS);
		}
		else if(product.getName().equalsIgnoreCase("Washing machine") ) {
			product.setCategory(Category.WHITE_GOODS);
		}
		else if(product.getName().equalsIgnoreCase("Vacuum cleaner") || product.getName().equalsIgnoreCase("Hoover") ) {
			product.setCategory(Category.SMALL_HOME_APPLIANCES);
		}
		else if(product.getName().equalsIgnoreCase("Iron")) {
			product.setCategory(Category.SMALL_HOME_APPLIANCES);
		}
		else if(product.getName().equalsIgnoreCase("Cooker")) {
			product.setCategory(Category.WHITE_GOODS);
		}
		else if(product.getName().equalsIgnoreCase("Hairdryer")) {
			product.setCategory(Category.SMALL_HOME_APPLIANCES);
		}
		else if(product.getName().equalsIgnoreCase("Camera")) {
			product.setCategory(Category.VIDEO);
		}
		else if(product.getName().equalsIgnoreCase("Video Camera")) {
			product.setCategory(Category.VIDEO);
		}
		else if(product.getName().equalsIgnoreCase("Computer") || product.getName().equalsIgnoreCase("Desktop") ) {
			product.setCategory(Category.COMPUTERS);
		}
		else if(product.getName().equalsIgnoreCase("Lap top") || product.getName().equalsIgnoreCase("Laptop")) {
			product.setCategory(Category.COMPUTERS);
		}
		else if(product.getName().equals("Microwave")) {
			product.setCategory(Category.KITCHEN_APPLIANCES);
		}
		else if(product.getName().equals("Mixer")) {
			product.setCategory(Category.KITCHEN_APPLIANCES);
		}
		else if(product.getName().equalsIgnoreCase("Music pillar") || product.getName().equalsIgnoreCase("Music line")) {
			product.setCategory(Category.AUDIO);
		}
		else if(product.getName().equalsIgnoreCase("Clock")) {
			product.setCategory(Category.SMALL_HOME_APPLIANCES);
		}
		else if(product.getName().equalsIgnoreCase("Tablet")) {
			product.setCategory(Category.COMPUTERS);
		}
		else if(product.getName().equalsIgnoreCase("Toaster")) {
			product.setCategory(Category.KITCHEN_APPLIANCES);
		}
		else if(product.getName().equalsIgnoreCase("Fan")) {
			product.setCategory(Category.SMALL_HOME_APPLIANCES);
		}
		else {
			product.setCategory(Category.TECHNICS);
		}
		return product;
	}


}
