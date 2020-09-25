package com.doggers.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doggers.demo.entity.Users;
import com.doggers.demo.service.UserService;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService; 

	@GetMapping("/all")
	public List<Users> allUsers(){
		return (List<Users>) userService.findAll();
	}
	
	//Read All Users
	@GetMapping
	public List<Users> readAll(){
		List<Users> users = StreamSupport.stream(userService.findAll().spliterator(), false)
										.collect(Collectors.toList());
		return users;
	}
	
	//Read a user
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id){
		
		Optional<Users> oUser = userService.findById(id);
		
		if(!oUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(oUser);

		
	}
	
	//Create a new User
	@PostMapping
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity<?> create(@RequestBody Users user){	
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
	}
	
	//Update User
	@PutMapping("/{id}")
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity<?> update(@RequestBody Users userDetails, @PathVariable(value = "id") Long userId){
		
		Optional<Users> oUser = userService.findById(userId);
		
		if(!oUser.isPresent()) {
			
			return ResponseEntity.notFound().build();
		}
		
		oUser.get().setName(userDetails.getName());
		oUser.get().setLastname(userDetails.getLastname());
		oUser.get().setEmail(userDetails.getEmail()); 
		oUser.get().setUsername(userDetails.getUsername()); 
		oUser.get().setEnabled(userDetails.getEnabled());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(oUser.get()));
		
		
	}
	
	//delete user
	@DeleteMapping("/{id}")
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		Optional<Users> user = userService.findById(id);
		
		if(!user.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		userService.deleteById(id);
		return ResponseEntity.ok().build();
		
	}
	
	
	
	
}
