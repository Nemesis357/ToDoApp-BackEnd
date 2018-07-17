package com.todoapp.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todoapp.service.ToDoService;
import com.todoapp.user.User;

@CrossOrigin
@RestController
public class MainController {
	@Autowired
	private ToDoService service;
	private BCryptPasswordEncoder encoder;
	
	@RequestMapping("/resource")
	  public Map<String,Object> home() {
	    Map<String,Object> model = new HashMap<String,Object>();
	    model.put("id", UUID.randomUUID().toString());
	    model.put("content", "Hello World");
	    return model;
	 }
	
	@PostMapping("/sign-up")
    public void signUp(@RequestBody User user) {
		System.out.println("Inside of sign-up. Email: " + user.getEmail());
//        user.setPassword(encoder.encode(user.getPassword()));
        System.out.println("Password: " + user.getPassword());
        service.addUser(user);
    }
	
}
