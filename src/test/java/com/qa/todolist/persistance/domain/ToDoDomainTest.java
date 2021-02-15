package com.qa.todolist.persistance.domain;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ToDoDomainTest {
	
	private ToDoDomain toDo;
	
	
	
	@Test
	void setterGetterTest() {
		toDo = new ToDoDomain(1L, "Cleaning", null);
		
		Assertions.assertThat(toDo).isNotNull();
		toDo.setId(1L);
		toDo.setName("Something");
		toDo.setTaskList(null);
		
		Assertions.assertThat(toDo.getId()).isNotNull();
		Assertions.assertThat(toDo.getName()).isNotNull();
		Assertions.assertThat(toDo.getTaskList()).isNull();
	}

}
