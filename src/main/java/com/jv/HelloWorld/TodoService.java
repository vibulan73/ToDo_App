package com.jv.HelloWorld;

import com.jv.HelloWorld.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public Todo createTodo(Todo todo){
        return todoRepository.save(todo);
    }

    public Todo getTodoById(Long id){
        return todoRepository.findById(id).orElseThrow(()->new RuntimeException("Todo not found"));
    }
}
