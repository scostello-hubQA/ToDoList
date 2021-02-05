package com.qa.todolist.persistance.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.todolist.persistance.domain.ToDoDomain;

@Repository
public interface ToDoRepo extends JpaRepository<ToDoDomain, Long> {

}
