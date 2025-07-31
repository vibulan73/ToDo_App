package com.jv.HelloWorld;

import com.jv.HelloWorld.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

//CRUD
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
