package onlineshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import onlineshop.models.User;
import onlineshop.repository.UserRepository;
import onlineshop.service.UserService;

@Service
public class JpaUserService implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional
	public User  loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return User.build(user);
	}

	@Override
	public User getReferenceById(Integer id) {
		return userRepository.getReferenceById(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Page<User> findAll(int pageNum) {
		PageRequest pageable = PageRequest.of(pageNum, 30);
		return userRepository.findAll(pageable);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User delete(Integer id) {
		User user = userRepository.getReferenceById(id);
		if(user != null) {
			userRepository.delete(user);
		}
		return user;
	}

	@Override
	public Page<User> search(String username, String email, int pageNum) {
		if( username != null) {
			username = '%' + username + '%';
		}
		if( email != null) {
			email = '%' + email + '%';
		}
		PageRequest pageable = PageRequest.of(pageNum, 20);
		return userRepository.search(username, email, pageable);
	}

	@Override
	public User findbyUsername(String username) {
		return userRepository.findbyUsername(username);
	}

	@Override
	public Boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public Boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	



}
