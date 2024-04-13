package onlineshop.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import onlineshop.models.Category;

public class ProductDTO {
	
	private Integer id;
	
	@NotEmpty(message = "Please provide a name")
	private String name;
	
	@NotEmpty(message = "Please provide a brand")
	private String brand;
	
	@PositiveOrZero(message = "Only positive number")
	private Integer quantity;
	
	@Positive(message = "Only positive number")
	private Double price;
	
	private String photo;
	
	private Category category;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	
	
	

}
