package onlineshop.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="shopping")
public class Shopping {
	
	@Id
	@GeneratedValue
	@Column
	private Integer id;
	@Column
	private String code;
	@Column
	private Double totalPrice;
	@Column
	private Timestamp dateTimeT;
	@Column
	private String dateTime;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "user")
	private User user;
	
	
	@OneToMany(mappedBy="shopping", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	List<Item> items = new ArrayList<>();


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public Double getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}


	public Timestamp getDateTimeT() {
		return dateTimeT;
	}


	public void setDateTimeT(Timestamp dateTimeT) {
		this.dateTimeT = dateTimeT;
	}


	public String getDateTime() {
		return dateTime;
	}


	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
		if(!user.getShoppings().contains(this)){
			user.getShoppings().add(this);
		}
	}

	

	public List<Item> getItems() {
		return items;
	}


	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public void addItem(Item item) {
		if(item.getShopping() != this) {
			item.setShopping(this);
		}
		items.add(item);
	}
	
	
	
	
	

}
