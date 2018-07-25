package com.todoapp.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todoapp.exceptions.AppException;
import com.todoapp.model.Role;
import com.todoapp.model.RoleName;
import com.todoapp.model.Task;
import com.todoapp.model.User;
import com.todoapp.payload.ApiResponse;
import com.todoapp.payload.TaskRequest;
import com.todoapp.repository.RoleRepo;
import com.todoapp.repository.TaskRepo;
import com.todoapp.repository.UserRepo;

@CrossOrigin
@RestController
public class MainController {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	RoleRepo roleRepo;
	
	@Autowired
	TaskRepo taskRepo;
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody TaskRequest task) {
		Task newTask = new Task(task.getUserId(), task.getTitle(), task.getDate(), task.getContent());
		
		Task saved = taskRepo.save(newTask);
		
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "Post created successfully"));
	}
	
	@GetMapping("/getTasks/{id}")
	public List<?> getTasks(@PathVariable("id") Long userId) {
		List<Task> tasks = taskRepo.findAllByUserId(userId);
		return tasks;
	}
	
	
	@GetMapping("/isAdmin/{id}")
	public boolean isAdmin(@PathVariable("id") Long userId) {
		Optional<User> userIsPresent = userRepo.findById(userId);
		
		if(userIsPresent.isPresent()) {
		    User user = userIsPresent.get();
		    
		    try {
		    	Set<Role> roles = user.getRoles();
		    	
		    	Iterator<Role> iter = roles.iterator();
		    	Role first = iter.next();
		    	if(first.getName().equals(RoleName.ROLE_ADMIN)) return true;
		    	return false;
		    } catch (Exception e) {
		    	return false;
		    }
		    
		} else {
		    return false;
		}
	}
	
	@GetMapping("/getUsers")
	public List<String> getUsers() {
		return userRepo.getUsers();
	}
	
	@GetMapping("/getUser/{username}")
	public User getUSer(@PathVariable("username") String username) {
		return userRepo.findByUsername(username);		
	}
	
	@DeleteMapping("/deleteUser/{userId}")
	public void deleteUser(@PathVariable("userId") Long userId) {
		userRepo.deleteById(userId);
	}
	
	@GetMapping("/makeAdmin/{userId}")
	public boolean makeAdmin(@PathVariable("userId") Long userId) {
		Optional<User> userSearch = userRepo.findById(userId);
		
		if(userSearch.isPresent()) {
		    User user = userSearch.get();
		    
		    try {
		    	Role userRole = roleRepo.findByName(RoleName.ROLE_ADMIN)
		                .orElseThrow(() -> new AppException("User Role not set."));
		    	Set<Role> newUserRoles = new HashSet<>();
		        newUserRoles.add(userRole);
		        user.setRoles(newUserRoles);
		    	Set<Role> roles = user.getRoles();
		    	
		    	userRepo.save(user);
		    	return true;
		    } catch (Exception e) {
		    	e.printStackTrace();
		    	return false;
		    }
		}
		return false;
	}
	
	@GetMapping("/makeUser/{userId}")
	public boolean makeUser(@PathVariable("userId") Long userId) {
		Optional<User> userSearch = userRepo.findById(userId);
		
		if(userSearch.isPresent()) {
		    User user = userSearch.get();
		    
		    try {
		    	Role userRole = roleRepo.findByName(RoleName.ROLE_USER)
		                .orElseThrow(() -> new AppException("User Role not set."));
		    	Set<Role> newUserRoles = new HashSet<>();
		        newUserRoles.add(userRole);
		        user.setRoles(newUserRoles);
		    	Set<Role> roles = user.getRoles();
		    	
		    	userRepo.save(user);
		    	return true;
		    } catch (Exception e) {
		    	e.printStackTrace();
		    	return false;
		    }
		}
		return false;
	}
}
