package com.jv.HelloWorld.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data  //Both Getter Setter
public class Todo {
    @Id
    @GeneratedValue
    Long id;
    @NotBlank
    @NotNull
    @Schema(name= "title", example = "Complete Spring Boot")
    String title;
    Boolean isCompleted;
//    @Email
//    String email;
}
