package com.example.learnSpringBoot.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @NotNull(message = "userId can't be null")
    private String userId;
    
    @Email(message = "provide valid email")
    private String email;

    @Size(min = 3, message = "firstName should contain 3 characters")
    private String firstName;

    private String lastName;
}
