package com.newapp.service;

import java.util.List;

import com.newapp.models.User;

public interface UserService {
	User registerUser(User user); //done
	
	User findUserById(Integer id); // done
	
	User findUserByEmail(String email); //done
	
	User followUser(Integer followedUserId, Integer followingUserId); //
	
	User updateUser(Integer id, User userDetails); //done
	
	List<User> searchUsers(String query); //done
	

}
