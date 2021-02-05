package com.qa.todolist.service;

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
	
	
	

}
