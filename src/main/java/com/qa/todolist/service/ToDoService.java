package com.qa.todolist.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.todolist.persistance.domain.ToDoDomain;
import com.qa.todolist.persistance.dtos.ToDoDTO;
import com.qa.todolist.persistance.repos.ToDoRepo;

@Service
public class ToDoService {

	private ToDoRepo repo;
	private ModelMapper mapper;
	
	@Autowired
	public ToDoService(ToDoRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private ToDoDTO mapToDTO(ToDoDomain model) {
		return this.mapper.map(model, ToDoDTO.class);
	}
	
	public ToDoDTO create(ToDoDomain model) {
		return this.mapToDTO(this.repo.save(model));
		
	}
	
	public List<ToDoDTO> readAll(){
		
		List<ToDoDomain> dbList = this.repo.findAll();
		
		List<ToDoDTO> resultList = dbList.stream().map(this::mapToDTO).collect(Collectors.toList());
		return resultList;
		
	}
	
	public ToDoDTO readById(Long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow());
		
	}

	
	public ToDoDTO updateToDo(Long id, ToDoDomain newDetails) {
		this.repo.findById(id).orElseThrow();
		newDetails.setId(id);
		return this.mapToDTO(this.repo.save(newDetails));
		
	}
	
	public boolean deleteToDo(long id) {
	this.repo.deleteById(id);
	return !this.repo.existsById(id);
	}
	
}
