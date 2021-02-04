package com.qa.todolist.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.todolist.persistance.domain.TaskDomain;
import com.qa.todolist.persistance.dtos.TaskDTO;
import com.qa.todolist.persistance.repos.TaskRepo;

@Service
public class TaskService {
	
	private TaskRepo repo;
	private ModelMapper mapper;
	
	@Autowired
	public TaskService(TaskRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
		
	}
	
	private TaskDTO mapToDTO(TaskDomain model) {
		return this.mapper.map(model, TaskDTO.class);
	}
	
	
	//create
	public TaskDTO create(TaskDomain task) {
		return this.mapToDTO(this.repo.save(task));
		
	}
	
	//read all tasks
	public List<TaskDTO> readAll(){
		List<TaskDomain> dbList = this.repo.findAll();
		List<TaskDTO> resultList = dbList.stream().map(this::mapToDTO).collect(Collectors.toList());
		return resultList;
	}
	
	//read by ID
	
	public TaskDTO readById(Long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow());
	}
	
	//update
	
	public TaskDTO updateTask(Long id, TaskDomain newDetails) {
		this.repo.findById(id).orElseThrow();
		newDetails.setTaskId(id);
		return this.mapToDTO(this.repo.save(newDetails));
	}
	
	//delete
	
	public boolean deleteTask(long id) {
		this.repo.deleteById(id);
		boolean flag = !this.repo.existsById(id);
		return flag;
	}
	

}
