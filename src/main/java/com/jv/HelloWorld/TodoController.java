package com.jv.HelloWorld;

import com.jv.HelloWorld.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;
//    private Object HttpStatus;

    @GetMapping("/get")
    String getTodo(){
        return "Todoss";
    }

    //Path Variable
    @GetMapping("/{id}")
    ResponseEntity<Todo> getTodoById(@PathVariable long id ){
        return new ResponseEntity<>(todoService.getTodoById(id), HttpStatus.OK);
    }

    //Request Param
    @GetMapping
    String getTodoByIdParam(@RequestParam("todoId") long id ){
        return "Todo with ID " + id;
    }
//    wrong as password is visible to all users
//    @GetMapping("/create")
//    String createUser(@RequestParam String userId, @RequestParam String password ){
//        return "Todo with ID " + userId + " " + "password: " + password;
//    }
    @PostMapping("/create")
    ResponseEntity<Todo> createUser(@RequestBody Todo todo){
        return new ResponseEntity<>(todoService.createTodo(todo), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    String updateTodoById(@PathVariable long id){
        return "Update Todo with ID " + id;
    }

    @DeleteMapping("/{id}")
    String deleteTodoById(@PathVariable long id){
        return "Delete Todo with ID " + id;
    }
}
