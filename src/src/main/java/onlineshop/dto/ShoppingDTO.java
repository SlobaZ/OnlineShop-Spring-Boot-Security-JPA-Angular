package onlineshop.dto;

import java.sql.Timestamp;

public class ShoppingDTO {
	
	private Integer id;
	private String code;
	private Double totalPrice;
	private Timestamp dateTimeT;
	private String dateTime;
	
	private Long userId;
	private String userUsername;
	
	
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
	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserUsername() {
		return userUsername;
	}
	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}
	
	

}
