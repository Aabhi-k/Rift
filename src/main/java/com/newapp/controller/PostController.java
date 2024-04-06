package com.newapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newapp.models.Post;
import com.newapp.response.ApiResponse;
import com.newapp.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	PostService service;
	
	@GetMapping("/{postId}")
	public ResponseEntity<Post> findPostById(@PathVariable("postId") Integer postId) {
		return ResponseEntity.ok(service.findPostById(postId));
	}
	
	@GetMapping("/u/{userId}")
	public ResponseEntity<List<Post>> fidUsersPost(@PathVariable("userId") Integer userId ){
		return ResponseEntity.ok(service.findPostByUserId(userId));
	}
	
	
	@PostMapping("/create/{userId}")
	public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable("userId")Integer userId) throws Exception{
		
		return ResponseEntity.ok(service.createNewPost(post, userId));
	}
	
	@DeleteMapping("/{postId}/{userId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Integer postId, @PathVariable("userId") Integer userId){
		ApiResponse res = new ApiResponse(service.deletePost(postId, userId), true);
		return ResponseEntity.ok(res);
	}
	
	@GetMapping
	public ResponseEntity<List<Post>> findAllPost(){
		return ResponseEntity.ok(service.findAllPost());
	}
	
	@PutMapping("/save/{postId}/{userId}")
	public ResponseEntity<Post> savedPost(@PathVariable("postId") Integer postId, @PathVariable("userId")Integer userId){
		return ResponseEntity.ok(service.savedPost(postId, userId));
		
	}
	@PutMapping("/like/{postId}/{userId}")
	public ResponseEntity<Post> likedPost(@PathVariable("postId") Integer postId, @PathVariable("userId")Integer userId){
		return ResponseEntity.ok(service.likedPost(postId, userId));
	}
	
	
	
	

}
