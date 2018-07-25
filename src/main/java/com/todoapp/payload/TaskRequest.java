package com.todoapp.payload;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskRequest {
	@NotBlank
	private Long userId;
	
	@NotBlank
    private String title;
    
	@JsonProperty("dueDate")
    @NotBlank
    private LocalDate dueDate;

    @NotBlank
    private String content;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getDate() {
		return dueDate;
	}

	public void setDate(LocalDate date) {
		this.dueDate = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
