package onlineshop.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

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
		if(!user.getShopping().contains(this)){
			user.getShopping().add(this);
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
