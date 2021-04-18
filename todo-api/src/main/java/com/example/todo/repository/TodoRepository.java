package com.example.todo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.todo.entities.TodoDao;

@Repository
public interface TodoRepository extends PagingAndSortingRepository<TodoDao, Long> {

	public List<TodoDao> findByUser_UserId(@Param("id") Long id);

	public List<TodoDao> findByUser_UserName(@Param("userName") String userName);
	
	public Page<TodoDao> findByUser_UserName(@Param("userName") String userName, Pageable pageable);

	@Modifying
	@Transactional
	@Query("update TodoDao t set t.task = :task, t.isCompleted = :completed, t.modifiedDate = :modifiedAt where t.id = :id")
	void updateTodo(@Param(value = "id") long id, @Param(value = "task") String task,
			@Param(value = "completed") Boolean completed, @Param(value = "modifiedAt") Date modifiedAt);
}
