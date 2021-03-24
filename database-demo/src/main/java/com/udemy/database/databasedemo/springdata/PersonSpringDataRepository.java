package com.udemy.database.databasedemo.springdata;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.database.databasedemo.entity.Person;

public interface PersonSpringDataRepository extends JpaRepository<Person, Integer>{

}
