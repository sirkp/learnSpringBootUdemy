package com.example.learnSpringBoot.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorMessage {
    private Date timestamp;
    private String message;
}
