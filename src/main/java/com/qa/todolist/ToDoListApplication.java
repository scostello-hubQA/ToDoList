package com.qa.todolist;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class ToDoListApplication {

	public static void main(String[] args) {
		ApplicationContext beanBag = SpringApplication.run(ToDoListApplication.class, args);
	
	
	
	System.out.println("Server time:\t" + beanBag.getBean("localTime", String.class));
	
	}

}
