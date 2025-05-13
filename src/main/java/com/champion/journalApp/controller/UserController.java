package com.champion.journalApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.champion.journalApp.entity.User;
import com.champion.journalApp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<?> getAll(){
		List<User> list = userService.getAll();
		if(list != null && !list.isEmpty())
			return new ResponseEntity<>(list, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody User user) {
		try {
			User createdUser = userService.saveUser(user);
			return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/{username}")
	public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String username){
		User updatedUser = userService.updateUser(user, username);
		if(user == null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		
	}
}
