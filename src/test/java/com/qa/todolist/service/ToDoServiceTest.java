package com.qa.todolist.service;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.qa.todolist.persistance.domain.ToDoDomain;

import com.qa.todolist.persistance.dtos.ToDoDTO;
import com.qa.todolist.persistance.repos.ToDoRepo;

@SpringBootTest
public class ToDoServiceTest {
	
	@MockBean
	private ModelMapper mockedMapper;
	
	@MockBean
	private ToDoRepo mockedRepo;
	
	@Autowired
	private ToDoService service;
	
	@Test
	public void create() {
		
		ToDoDomain testToDo = new ToDoDomain(1L, "Cleaning", null);
		ToDoDTO testDTO = new ToDoDTO(1L, "Cleaning", null);
		
		Mockito.when(this.mockedRepo.save(Mockito.any(ToDoDomain.class))).thenReturn(testToDo);
		Mockito.when(this.mockedMapper.map(testToDo, ToDoDTO.class)).thenReturn(testDTO);
		
		ToDoDTO result = this.service.create(testToDo);
		
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result).isEqualTo(testDTO);
		Assertions.assertThat(result).usingRecursiveComparison()
				.isEqualTo(testDTO);
		
		Mockito.verify(this.mockedRepo, Mockito.times(1)).save(Mockito.any(ToDoDomain.class));
		Mockito.verify(this.mockedMapper, Mockito.times(1)).map(testToDo, ToDoDTO.class);
	}
	
	@Test
	public void delete() {
		Long id = 1l;
		
		Mockito.when(this.mockedRepo.existsById(id)).thenReturn(false);
		
		Assertions.assertThat(this.service.deleteToDo(1l)).isTrue();
		
		Mockito.verify(this.mockedRepo, Mockito.times(1)).existsById(id);
	}
	
	@Test
	public void readById() {
		ToDoDomain testToDo = new ToDoDomain(1L, "Cleaning", null);
		ToDoDTO testDTO = this.mockedMapper.map(testToDo, ToDoDTO.class);
		
		Mockito.when(this.mockedRepo.findById(testToDo.getId())).thenReturn(Optional.of(testToDo));
		ToDoDTO result = this.service.readById(1L);
		
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(testDTO);
		
		Mockito.verify(this.mockedRepo, Mockito.times(1)).findById(1L);
		}
	
	@Test
	public void readAll() {
		Long id = 1L;
		ToDoDomain testToDo = new ToDoDomain(1L, "Cleaning", null);
		
		testToDo.setId(id);
		
		List<ToDoDomain>todos = this.mockedRepo.findAll();
		ToDoDTO resultList = this.mockedMapper.map(todos, ToDoDTO.class);
		
		Mockito.when(this.mockedRepo.findAll()).thenReturn(todos);
		Mockito.when(this.mockedMapper.map(todos, ToDoDTO.class)).thenReturn(resultList);
		
		Assertions.assertThat(todos).isNotNull();
		Assertions.assertThat(this.service.readAll()).isEqualTo(todos);
		
		Mockito.verify(this.mockedRepo, Mockito.times(2)).findAll();
	}
	
	@Test
	public void update() {
		ToDoDomain testToDo = new ToDoDomain(1L, "Cleaning", null);
		ToDoDomain newToDo = new ToDoDomain(1L, "Cleaning", null);
		ToDoDTO testDTO = new ToDoDTO(1L, newToDo.getName(), null);
		
		Mockito.when(this.mockedRepo.findById(1L)).thenReturn(Optional.of(testToDo));
		Mockito.when(this.mockedRepo.save(newToDo)).thenReturn(newToDo);
		Mockito.when(this.mockedMapper.map(newToDo, ToDoDTO.class)).thenReturn(testDTO);
		
		ToDoDTO result = this.service.updateToDo(1L, newToDo);
		
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(testDTO);
	}
	
	

}
