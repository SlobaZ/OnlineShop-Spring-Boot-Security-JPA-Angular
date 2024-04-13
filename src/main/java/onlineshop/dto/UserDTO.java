package onlineshop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDTO {
	
	private Integer id;
	
	@NotEmpty(message = "Please provide a username")
	@Size(min=5, max=100, message = "Username must be between 5 and 100 character")
	private String username;
	
	@NotBlank(message = "Please provide a e-mail")
	@Size(max=100)
	@Email
	private String email;
	
	@NotEmpty(message = "Please provide a password")
	@Size(min=6, max=100, message = "Password must be between 6 and 100 character")
	private String password;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
