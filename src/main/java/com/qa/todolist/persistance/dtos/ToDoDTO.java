package com.qa.todolist.persistance.dtos;

import java.util.List;

public class ToDoDTO {
	
	private Long id;
	
	private String name;
	
	private List<TaskDTO> taskList;
	

	public ToDoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public ToDoDTO(Long id, String name, List<TaskDTO> taskList) {
		super();
		this.id = id;
		this.name = name;
		this.taskList = taskList;
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



	public List<TaskDTO> getTaskList() {
		return taskList;
	}



	public void setTaskList(List<TaskDTO> taskList) {
		this.taskList = taskList;
	}



	

}
