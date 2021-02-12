package com.qa.todolist.config;

import java.time.LocalTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	public static Logger LOGGER = LogManager.getLogger();
	
	@Bean
	public String localTime() {
		return LocalTime.now().toString();
	}
	
	@Bean
	public ModelMapper getMapper() {
		return new ModelMapper();
	}
	


}
