package com.qa.todolist.persistance.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TaskDomainTest {
	
	private TaskDomain task;
	private TaskDomain other;
	
	@Test
	public void setterGetterTest() {
		task = new TaskDomain(1L, "Hoover", 2, "01-01-01", "Hoover downstairs", false, null);
		other = new TaskDomain(1L, "Hoover", 2, "01-01-01", "Hoover downstairs", false, null); 
		
		
		Assertions.assertThat(task).isNotNull();
		task.setTaskId(2L);
		task.setAct("Hoover");
		task.setPriority(2);
		task.setDate("22-02-2020");
		task.setNotes("testing");
		task.setCompleted(true);
		task.setToDo(null);
		Assertions.assertThat(task.getTaskId()).isNotNull();
		Assertions.assertThat(task.getAct()).isNotNull();
		Assertions.assertThat(task.getPriority()).isNotNull();
		Assertions.assertThat(task.getDate()).isNotNull();
		Assertions.assertThat(task.getNotes()).isNotNull();
		Assertions.assertThat(task.isCompleted()).isEqualTo(true);
		Assertions.assertThat(task.getToDo()).isNull();
		
		
	}
	

}
