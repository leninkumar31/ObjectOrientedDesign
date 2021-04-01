package com.example.todo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.todo.entities.UserDao;

@Repository
public interface UserRepository extends CrudRepository<UserDao, Long> {
	
	@Query("SELECT t FROM UserDao t WHERE t.userName = :userName")
	public Optional<UserDao> validateUserName(@Param("userName") String userName);

	@Query("SELECT t FROM UserDao t WHERE t.userName = :userName AND t.password = :password")
	public Optional<UserDao> validateUser(@Param("userName") String userName, @Param("password") String password);
}
