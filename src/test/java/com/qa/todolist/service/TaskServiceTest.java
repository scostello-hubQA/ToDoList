package com.qa.todolist.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
	

}
