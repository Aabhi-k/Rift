package com.newapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.newapp.models.Post;
import com.newapp.models.PostRepository;
import com.newapp.models.User;
import com.newapp.models.UserRepository;

@Service
public class PostServiceImpl implements PostService{

	
	@Autowired UserService us; @Autowired UserRepository userRepository; @Autowired PostRepository postRepository;
	@Override
	public Post createNewPost(Post post, Integer userId) throws Exception{
		// TODO Auto-generated method stub
		post.setCreatedAt(LocalDateTime.now());  
		post.setUser(us.findUserById(userId));
		postRepository.save(post);
		
		return null;
	}

	@Override
	public String deletePost(Integer postId, Integer userId) {
		// TODO Auto-generated method stub
		Post post = findPostById(postId);
		User user = us.findUserById(userId);
		
		if(post.getUser().getId() == user.getId()) {
			postRepository.delete(post);
			return "Post Deleted";
			
		}
		else
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "cannot delete post");
		
	}

	@Override
	public List<Post> findPostByUserId(Integer userId) {
		// TODO Auto-generated method stub
		List<Post> userPosts = postRepository.findPostByUserId(userId);
		
		return userPosts;
	}

	@Override
	public Post findPostById(Integer postId) {
		// TODO Auto-generated method stub
		
		Optional<Post>posts = postRepository.findById(postId);
		if(posts.isEmpty()){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No post");
			
		}
		return posts.get();
	}

	@Override
	public List<Post> findAllPost() {
		// TODO Auto-generated method stub
		return postRepository.findAll();
	}

	@Override
	public Post savedPost(Integer postId, Integer userId) {
		// TODO Auto-generated method stub
		Post post = findPostById(postId);
		User user = us.findUserById(userId);
		
		if(user.getSavedPost().contains(post)) {
			user.getSavedPost().remove(post);
		}else
			user.getSavedPost().add(post);
		userRepository.save(user);
		
		return post;
	}

	@Override
	public Post likedPost(Integer postId, Integer userId) {
		// TODO Auto-generated method stub
		Post post = findPostById(postId);
		User user = us.findUserById(userId);
		
		if(post.getLikedUser().contains(user)) {
			post.getLikedUser().remove(user);
		}else
			post.getLikedUser().add(user);
		
		return postRepository.save(post);
	}

}
