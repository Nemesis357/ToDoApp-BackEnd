package com.todoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoapp.model.User;
import com.todoapp.repository.UserRepo;

@Service
public class ToDoService {
	@Autowired
	private UserRepo userRepo;
	
	public void addUser(User user) {
		this.userRepo.save(user);
	}
}
