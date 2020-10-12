package com.doggers.demo.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.doggers.demo.entity.Users;
import com.doggers.demo.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Users> findAll() {
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Users> findAll(Pageable pageable) {
		
		return userRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Users> findById(Long id) {
		
		return userRepository.findById(id);
	}

	@Override
	@Transactional
	public Users save(Users reg) {

		return userRepository.save(reg);
		
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		
		userRepository.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Users user = userRepository.findByEmail(email);
		
		if(user == null) {
				logger.error("Error");
				throw new UsernameNotFoundException("Error");
		}
		
		List<GrantedAuthority> authorities = user.getRoles()
				                                 .stream()
				                                 .map(role -> new SimpleGrantedAuthority(role.getName()))
				                                 .peek(authority -> logger.info("Role: "+ authority.getAuthority()))
				                                 .collect(Collectors.toList());
		
		return new User(user.getEmail(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
	}

	@Override
	public Users findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
