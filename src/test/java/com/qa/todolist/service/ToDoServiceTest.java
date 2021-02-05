package com.qa.todolist.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.todolist.persistance.domain.TaskDomain;
import com.qa.todolist.persistance.domain.ToDoDomain;
import com.qa.todolist.persistance.dtos.TaskDTO;
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
	
	
	

}
