package com.qa.todolist.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.todolist.persistance.domain.TaskDomain;
import com.qa.todolist.persistance.dtos.TaskDTO;
import com.qa.todolist.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {
	
	private TaskService service;

	public TaskController(TaskService service) {
		super();
		this.service = service;
		
	}
	
	@GetMapping("/readAll")
	public ResponseEntity<List<TaskDTO>> readAll(){
		return ResponseEntity.ok(this.service.readAll());
	}
	
	@GetMapping("/read/{id}")
	public ResponseEntity<TaskDTO> readByTaskId(@PathVariable("id") Long id){
		return ResponseEntity.ok(this.service.readById(id));
	}
	
	@PostMapping("/create")
	public ResponseEntity<TaskDTO> create(@RequestBody TaskDomain task){
		return new ResponseEntity<TaskDTO>(this.service.create(task), HttpStatus.CREATED);
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<TaskDTO> updateTask(@PathVariable("id") Long id, @RequestBody TaskDomain task){
		return ResponseEntity.ok(this.service.updateTask(id, task));
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteTask(@PathVariable("id") Long id){
		return this.service.deleteTask(id)?
				new ResponseEntity<>(HttpStatus.NO_CONTENT):
					new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}
