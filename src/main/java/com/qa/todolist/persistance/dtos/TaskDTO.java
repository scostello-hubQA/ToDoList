package com.qa.todolist.persistance.dtos;



public class TaskDTO {

	private Long taskId; 
	
	
	private String act;
	
	
	private int priority;
	
	
	private String date;
	
	
	private String notes;
	
	
	private boolean completed;


	public TaskDTO() {
		super();
	}


	public TaskDTO(Long taskId, String act, int priority, String date, String notes, boolean completed) {
		super();
		this.taskId = taskId;
		this.act = act;
		this.priority = priority;
		this.date = date;
		this.notes = notes;
		this.completed = completed;
	}


	public Long getTaskId() {
		return taskId;
	}


	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}


	public String getAct() {
		return act;
	}


	public void setAct(String act) {
		this.act = act;
	}


	public int getPriority() {
		return priority;
	}


	public void setPriority(int priority) {
		this.priority = priority;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public boolean isCompleted() {
		return completed;
	}


	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	


}
