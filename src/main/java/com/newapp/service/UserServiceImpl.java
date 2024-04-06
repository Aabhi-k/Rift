package com.newapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.newapp.models.User;
import com.newapp.models.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;

	@Override
	public User registerUser(User user) {
		User savedUser = userRepository.save(user); //saving the user details to the DB
		return savedUser;
	}

	@Override
	public User findUserById(Integer id) {
		Optional<User> findUser = userRepository.findById(id);
		if(findUser.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User doesn't exist with id" + id);
		}
		
		return findUser.get();
	}

	@Override
	public User findUserByEmail(String email) {
		Optional<User> findUser = userRepository.findByEmail(email);
		if(findUser.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find email");
		}
		
		return findUser.get();
	}

	@Override
	public User followUser(Integer followedUserId, Integer followingUserId) {
		// TODO Auto-generated method stub
		User followedUser = findUserById(followedUserId);
		User followingUser = findUserById(followingUserId);
		
		System.out.println(followedUser.getFirstName());
		followingUser.getFollowersName().add(followedUser.getFirstName());
		followedUser.getFollowingsName().add(followingUser.getFirstName());
		
		System.out.println(followingUser.getFollowersName());
		
		userRepository.save(followingUser);
		userRepository.save(followedUser);

		return followingUser;
	}

	@Override
	public User updateUser(Integer id, User userDetails) {
		// TODO Auto-generated method stub
		User user = findUserById(id);
		if(userDetails.getFirstName() != null) {
			user.setFirstName(userDetails.getFirstName());
		}
		if(userDetails.getLastName() != null) {
			user.setLastName(userDetails.getLastName());
		}
		if(userDetails.getEmail()!=null) {
			user.setEmail(userDetails.getEmail());
		}
		userRepository.save(user);
		
		return user;
	}

	@Override
	public List<User> searchUsers(String query) {
		// TODO Auto-generated method stub
		
		List<User> users = userRepository.searchUser(query);
		
		
		return users;
	}

	

}
