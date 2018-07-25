package com.todoapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoapp.model.Task;

public interface TaskRepo extends JpaRepository<Task, Long> {
	List<Task> findAllByUserId(Long userId);
}
