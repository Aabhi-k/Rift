package com.newapp.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private List<String> followersName = new ArrayList<>(); 
	private List<String> followingsName = new ArrayList<>();
	@OneToMany(mappedBy="Post")
	private List<Post> savedPost = new ArrayList<>();

	
	
	public User() {}	
	
	
	
	
	public User( String firstName, String lastName, String email, String password,
			List<String> followersName, List<String> followingsName, List<Post> savedPost){
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.followersName = followersName;
		this.followingsName = followingsName;
		this.savedPost = savedPost;

	}




	public List<Post> getSavedPost() {
		return savedPost;
	}


	public void setSavedPost(List<Post> savedPost) {
		this.savedPost = savedPost;
	}


	public List<String> getFollowersName() {
		return followersName;
	}


	public void setFollowersName(List<String> followersName) {
		this.followersName = followersName;
	}


	public List<String> getFollowingsName() {
		return followingsName;
	}


	public void setFollowingsName(List<String> followingsName) {
		this.followingsName = followingsName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	
}
