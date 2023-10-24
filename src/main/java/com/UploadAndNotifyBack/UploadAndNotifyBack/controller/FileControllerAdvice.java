package com.UploadAndNotifyBack.UploadAndNotifyBack.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.UploadAndNotifyBack.UploadAndNotifyBack.storage.StorageException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class FileControllerAdvice {
    
    @ExceptionHandler({ StorageException.class, NullPointerException.class })
    public String handleStorageException(HttpServletResponse response){
        response.setStatus(500);
        return "storage exception or null pointer !";
    }
}
