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


import com.qa.todolist.persistance.domain.TaskDomain;
import com.qa.todolist.persistance.dtos.TaskDTO;
import com.qa.todolist.persistance.repos.TaskRepo;

@SpringBootTest
public class TaskServiceTest {
	
	@MockBean
	private ModelMapper mockedMapper;
	
	@MockBean
	private TaskRepo mockedRepo;
	
	@Autowired
	private TaskService service;
	
	@Test
	public void create() {
		
		TaskDomain testTask = new TaskDomain(1l, "Hoovering", 2, "22-01-2020", "kitchen hoover", false, null);
		TaskDTO testDTO = new TaskDTO(1l, "Hoovering", 2, "22-01-2020", "kitchen hoover", false);
		
		Mockito.when(this.mockedRepo.save(Mockito.any(TaskDomain.class))).thenReturn(testTask);
		Mockito.when(this.mockedMapper.map(testTask, TaskDTO.class)).thenReturn(testDTO);
		
		TaskDTO result = this.service.create(testTask);
		
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result).isEqualTo(testDTO);
		Assertions.assertThat(result).usingRecursiveComparison()
				.isEqualTo(testDTO);
		
		Mockito.verify(this.mockedRepo, Mockito.times(1)).save(Mockito.any(TaskDomain.class));
		Mockito.verify(this.mockedMapper, Mockito.times(1)).map(testTask, TaskDTO.class);
	}
	
	@Test
	public void delete() {
		Long id = 1l;
		
		Mockito.when(this.mockedRepo.existsById(id)).thenReturn(false);
		
		Assertions.assertThat(this.service.deleteTask(id)).isTrue();
		
		Mockito.verify(this.mockedRepo, Mockito.times(1)).existsById(id);
	}
	
	@Test
	public void readById() {
		TaskDomain testTask = new TaskDomain(1l, "Hoovering", 2, "22-01-2020", "kitchen hoover", false, null);
		TaskDTO testDTO = this.mockedMapper.map(testTask, TaskDTO.class);
		
		Mockito.when(this.mockedRepo.findById(testTask.getTaskId())).thenReturn(Optional.of(testTask));
		TaskDTO result = this.service.readById(1L);
		
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(testDTO);
		
		Mockito.verify(this.mockedRepo, Mockito.times(1)).findById(1L);
		}
	
	@Test
	public void readAll() {
		Long id = 1L;
		TaskDomain testTask = new TaskDomain(1l, "Hoovering", 2, "22-01-2020", "kitchen hoover", false, null);
		
		
		testTask.setTaskId(id);
		
		List<TaskDomain>tasks = this.mockedRepo.findAll();
		TaskDTO resultList = this.mockedMapper.map(tasks, TaskDTO.class);
		
		Mockito.when(this.mockedRepo.findAll()).thenReturn(tasks);
		Mockito.when(this.mockedMapper.map(tasks, TaskDTO.class)).thenReturn(resultList);
		

		
		Assertions.assertThat(tasks).isNotNull();
		Assertions.assertThat(this.service.readAll()).isEqualTo(tasks);
		
		Mockito.verify(this.mockedRepo, Mockito.times(2)).findAll();
		

	}
	
	@Test
	public void update() {
		TaskDomain testTask = new TaskDomain(1l, "Hoovering", 2, "22-01-2020", "kitchen hoover", false, null);
		TaskDomain newTask = new TaskDomain(1l, "Hoovering", 2, "22-01-2020", "kitchen hoover", false, null);
		TaskDTO testDTO = new TaskDTO(1L, newTask.getAct(), newTask.getPriority(), newTask.getDate(), newTask.getNotes(), false);
		
		Mockito.when(this.mockedRepo.findById(1L)).thenReturn(Optional.of(testTask));
		Mockito.when(this.mockedRepo.save(newTask)).thenReturn(newTask);
		Mockito.when(this.mockedMapper.map(newTask, TaskDTO.class)).thenReturn(testDTO);
		
		TaskDTO result = this.service.updateTask(1L, newTask);
		
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(testDTO);
	}
	

}
