package onlineshop.controllers;

import java.util.List;


import javax.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import onlineshop.dto.UserDTO;
import onlineshop.models.User;
import onlineshop.service.UserService;
import onlineshop.support.UserDTOToUser;
import onlineshop.support.UserToUserDTO;




@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping(value="/users")
public class ApiUserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserToUserDTO toDTO;
	
	@Autowired
	private UserDTOToUser toUser;
	
	@Autowired
	private PasswordEncoder encoder;
	
	
	@GetMapping("/sve")
	@PreAuthorize("hasRole('ADMIN')")
	ResponseEntity<List<UserDTO>> getAll() {
		List<User> UserPage = null;
		UserPage = userService.findAll();
		return new ResponseEntity<>( toDTO.convert(UserPage) , HttpStatus.OK);
	}	
		
	
	
	
	@GetMapping()
	@PreAuthorize("hasRole('ADMIN')")
	ResponseEntity<List<UserDTO>> getAllUsers(
			@RequestParam(required=false) String username,
			@RequestParam(required=false) String email,
			@RequestParam(value="pageNum", defaultValue="0") int pageNum){
		
		Page<User> UserPage = null;
		
		if(username != null || email != null ) {
			UserPage = userService.search(username, email, pageNum);
		}
		else {
			UserPage = userService.findAll(pageNum);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(UserPage.getTotalPages()) );
		
		return new ResponseEntity<>( toDTO.convert(UserPage.getContent()) , headers , HttpStatus.OK);
	}

	
	
	
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
		User User = userService.getById(id);
		if(User==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>( toDTO.convert(User), HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	ResponseEntity<UserDTO> deleteUser(@PathVariable Long id){
		User deleted = userService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>( toDTO.convert(deleted), HttpStatus.OK);
	}
	
	
	@PostMapping(consumes = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserDTO> addUser( @Valid @RequestBody UserDTO newUserDTO){
		
		if(newUserDTO==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		User savedUser = userService.save(toUser.convert(newUserDTO));
		
		return new ResponseEntity<>( toDTO.convert(savedUser), HttpStatus.CREATED);
	}
	
	
	
	@PutMapping(value="/{id}" , consumes = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserDTO> updateUser(@PathVariable Long id,
			@Valid  @RequestBody UserDTO userDTO ){
		
		User persisted = userService.getById(id);
		persisted.setUsername(userDTO.getUsername());
		persisted.setEmail(userDTO.getEmail());
		persisted.setPassword(encoder.encode(userDTO.getPassword()));
		
		userService.save(persisted);
		
		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}
	
	
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	

	
	
}
