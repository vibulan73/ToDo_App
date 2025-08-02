package com.jv.HelloWorld;

import com.jv.HelloWorld.models.Todo;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
@Slf4j
public class TodoController {
    @Autowired
    private TodoService todoService;
//    private Object HttpStatus;

    @GetMapping("/get")
    String getTodo(){
        return "Todoss";
    }

    //Path Variable
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Todo retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Todo was not found")
    })
    @GetMapping("/{id}")
    ResponseEntity<Todo> getTodoById(@PathVariable long id ){
        try {
            Todo createdTodo = todoService.getTodoById(id);
            return new ResponseEntity<>(createdTodo, HttpStatus.OK);
        }catch (RuntimeException exception) {
            log.info("Error");
            log.warn("");
            log.error("",exception);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    ResponseEntity<List<Todo>> getTodos(){
        return new ResponseEntity<List<Todo>>(todoService.getTodos(), HttpStatus.OK);
    }
    @GetMapping("/page")
    ResponseEntity<Page<Todo>> getTodosPaged(@RequestParam int page, @RequestParam int size){
        return new ResponseEntity<>(todoService.getAllTodosPages(page,size), HttpStatus.OK);
    }

//    //Request Param
//    @GetMapping
//    String getTodoByIdParam(@RequestParam("todoId") long id ){
//        return "Todo with ID " + id;
//    }

//    wrong as password is visible to all users
//    @GetMapping("/create")
//    String createUser(@RequestParam String userId, @RequestParam String password ){
//        return "Todo with ID " + userId + " " + "password: " + password;
//    }
    @PostMapping("/create")
    ResponseEntity<Todo> createUser(@RequestBody Todo todo){
        return new ResponseEntity<>(todoService.createTodo(todo), HttpStatus.CREATED);
    }

//    @PutMapping("/{id}")
//    String updateTodoById(@PathVariable long id){
//        return "Update Todo with ID " + id;
//    }
    @PutMapping
    ResponseEntity<Todo> updateTodoById(@RequestBody Todo todo){
        return new ResponseEntity<>(todoService.updateTodo(todo), HttpStatus.OK);
    }
//    @DeleteMapping("/{id}")
//    String deleteTodoById(@PathVariable long id){
//        return "Delete Todo with ID " + id;
//    }
    @DeleteMapping("/{id}")
    void deleteTodoById(@PathVariable long id){
        todoService.deleteTodoById(id);
    }

}
