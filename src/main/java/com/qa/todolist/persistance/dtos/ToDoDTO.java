package com.qa.todolist.persistance.dtos;

public class ToDoDTO {
	
	private Long id;
	
	private String name;

	public ToDoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ToDoDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
