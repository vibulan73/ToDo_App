package com.jv.HelloWorld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;
    @GetMapping("/get")
    String getTodo(){
        todoService.printTodos();
        return "Todoss";
    }

    //Path Variable
    @GetMapping("/{id}")
    String getTodoById(@PathVariable long id ){
        return "Todo with ID " + id;
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
    String createUser(@RequestBody String body){
        return body;
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
