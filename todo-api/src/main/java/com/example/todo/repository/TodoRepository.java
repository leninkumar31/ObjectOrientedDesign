package com.example.todo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.todo.entities.TodoDao;

@Repository
public interface TodoRepository extends CrudRepository<TodoDao, Long> {
	
	public List<TodoDao> findByUser_UserId(@Param("id") Long id);
}
