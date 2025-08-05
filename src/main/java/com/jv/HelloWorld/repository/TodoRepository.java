package com.jv.HelloWorld.repository;

import com.jv.HelloWorld.models.Todo;
import com.jv.HelloWorld.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//CRUD
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
