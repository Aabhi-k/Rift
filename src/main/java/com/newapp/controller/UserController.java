package com.newapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.newapp.models.User;
import com.newapp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService service;
	
	@GetMapping("/{userId}")
	public ResponseEntity<User> findUserById(@PathVariable("userId") Integer id) {

		return ResponseEntity.ok(service.findUserById(id));
	}
	
	@GetMapping("/search")
	public List<User> searchUser(@RequestParam("query") String query){

		return (service.searchUsers(query));
		
	}
	
	@GetMapping
	public User findUserByEmail(@RequestParam("email") String email) {
		return service.findUserByEmail(email);
	}
	
	
	@PostMapping
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		try {
			return ResponseEntity.ok(service.registerUser(user));
		}
		catch(Exception e) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "InvalidUser data");
		}
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable("userId") Integer id, @RequestBody User userDetails){
		
		return ResponseEntity.ok(service.updateUser(id, userDetails));
		
	}
	@PutMapping("/follow/{followedUser}/{followingUser}")
	public User followUserHandler(@PathVariable("followedUser") Integer followedUserId, @PathVariable("followingUser") Integer followingUserId) {
		return service.followUser(followedUserId, followingUserId);
	}
	
	
}
