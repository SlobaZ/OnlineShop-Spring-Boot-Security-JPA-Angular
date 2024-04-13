package onlineshop.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="item")
public class Item {
	
	@Id
	@GeneratedValue
	@Column
	private Integer id;
	
	@Column
	private Integer itemQuantity;
	
	@Column
	private Double itemPrice;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "shopping")
	private Shopping shopping;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "product")
	private Product product;
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(Integer itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	

	public Shopping getShopping() {
		return shopping;
	}

	public void setShopping(Shopping shopping) {
		this.shopping = shopping;
		if(!shopping.getItems().contains(this)){
			shopping.getItems().add(this);
		}
	}

	
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
		if(!product.getItems().contains(this)){
			product.getItems().add(this);
		}
	}
	
	
	

}
