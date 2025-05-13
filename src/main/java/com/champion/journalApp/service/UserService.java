package com.champion.journalApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.champion.journalApp.entity.User;
import com.champion.journalApp.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User saveUser(User user) {
		userRepository.save(user);
		return user;
	}
	
	public List<User> getAll(){
		return userRepository.findAll()	;
	}
	
	public User getById(Long id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}
	
	@Transactional
	public User updateUser(User newUser, String username) {
		User oldUser = userRepository.findByUsername(username);
		if(oldUser != null) {
			oldUser.setUsername(newUser.getUsername() != null && !newUser.getUsername().equals("") ? newUser.getUsername() : oldUser.getUsername());
			oldUser.setPassword(newUser.getPassword() != null && !newUser.getPassword().equals("") ? newUser.getPassword() : oldUser.getPassword());
			return oldUser;
		}
		else
			return null;
	}
	
	public User getByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
