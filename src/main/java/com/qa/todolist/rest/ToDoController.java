package com.qa.todolist.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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


import com.qa.todolist.persistance.domain.ToDoDomain;
import com.qa.todolist.persistance.dtos.ToDoDTO;
import com.qa.todolist.service.ToDoService;

@RestController
@RequestMapping("/todo")
public class ToDoController {

	private ToDoService service;

	@Autowired
	public ToDoController(ToDoService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/readAll")
	public ResponseEntity<List<ToDoDTO>> readAll(){
		return ResponseEntity.ok(this.service.readAll());
	}
	
	
	@GetMapping("/read/{id}")
	public ResponseEntity<ToDoDTO> readByToDoId(@PathVariable("id") Long id){
		return ResponseEntity.ok(this.service.readById(id));
	}
		
	
	
	
	@PostMapping("/create")
	public ResponseEntity<ToDoDTO> create(@RequestBody ToDoDomain model){
		return new ResponseEntity<ToDoDTO>(this.service.create(model), HttpStatus.CREATED);
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ToDoDTO> updateByHouseId(@PathVariable("id") Long id, @RequestBody ToDoDomain model){
		return ResponseEntity.ok(this.service.updateToDo(id, model));
	}
	
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteHouse(@PathVariable("id") Long id){
		return this.service.deleteToDo(id) ?
				new ResponseEntity<>(HttpStatus.NO_CONTENT) : 
					new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
