package org.example.controller;

import org.example.dto.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultAdvice {

    private static final Logger logger = LoggerFactory.getLogger(DefaultAdvice.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MessageDTO> illegalArgumentException(IllegalArgumentException e) {
        String response = "Division by zero!";
        logger.error(response, e);
        return new ResponseEntity<>(new MessageDTO(response), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<MessageDTO> numberFormatException(IllegalArgumentException e) {
        String response = "Invalid value!";
        logger.error(response, e);
        return new ResponseEntity<>(new MessageDTO(response), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<MessageDTO> accessDenied(AccessDeniedException e) {
        String response = "Access denied!";
        logger.error(response, e);
        return new ResponseEntity<>(new MessageDTO(response), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<MessageDTO> allException(Throwable e){
        String response = "Server error";
        logger.error(response, e);
        return new ResponseEntity<>(new MessageDTO(response), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

