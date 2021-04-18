package com.example.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.todo.entities.TodoDao;

@Repository
public interface TodoRepository extends CrudRepository<TodoDao, Long> {
	
	public List<TodoDao> findByUser_UserId(@Param("id") Long id);
	
	public List<TodoDao> findByUser_UserName(@Param("userName") String userName);
	
	@Modifying
	@Transactional
	@Query("update TodoDao t set t.task = :task, t.isCompleted = :completed where t.id = :id")
	void updateTodo(@Param(value = "id") long id, @Param(value = "task") String task, @Param(value = "completed") Boolean completed);
}
