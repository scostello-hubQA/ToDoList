package com.qa.todolist.rest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.todolist.persistance.domain.TaskDomain;
import com.qa.todolist.persistance.dtos.TaskDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts =  {"classpath:schema-test.sql", "classpath:data-test.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class TaskControllerIntergrationTest {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ObjectMapper jsonifier; 
	
	private final int ID = 1;
	
	private TaskDTO mapToDTO(TaskDomain model) {
		return this.mapper.map(model, TaskDTO.class);
	}
	
	
	
	@Test
	public void readAll() throws Exception{
		
		
		TaskDTO expectedResult1 = new TaskDTO(1L, "Practice", 3, "22/06/2022", "this is a test to make sure we can prepopulate the database", false);
		TaskDTO expectedResult2 = new TaskDTO(2L, "Hoover", 4, "21/06/2022", "need to hoover all of downstairs", false);
		TaskDTO expectedResult3 = new TaskDTO(3L, "Dishes", 3, "21/06/2022", "fill the dishwasher", false);
		
		List<TaskDTO>taskDTOList = new ArrayList<TaskDTO>();
		
		taskDTOList.add(expectedResult1);
		taskDTOList.add(expectedResult2);
		taskDTOList.add(expectedResult3);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "http://localhost:8080/task/readAll").accept(org.springframework.http.MediaType.APPLICATION_JSON);
		
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(taskDTOList));
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
		
		
	}
	
	@Test
	public void readById() throws Exception{
		
		TaskDTO expectedResult1 = new TaskDTO(1L, "Practice", 3, "22/06/2022", "this is a test to make sure we can prepopulate the database", false);
		//set it up
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "http://localhost:8080/task/read/" + ID);
		//set the expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult1));
		//perform the actions
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

}
