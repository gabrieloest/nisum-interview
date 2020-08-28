package com.nisum.interview.advice;

import com.nisum.interview.dto.ResponseError;
import com.nisum.interview.exception.EmailAlreadyInUseException;
import com.nisum.interview.exception.InvalidEmailFormatException;
import com.nisum.interview.exception.InvalidPasswordFormatException;
import com.nisum.interview.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
class UserExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseError userNotFoundHandler(UserNotFoundException ex) {
      return new ResponseError(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(InvalidEmailFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseError invalidEmailFormatHandler(InvalidEmailFormatException ex) {
        return new ResponseError(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(InvalidPasswordFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseError invalidPasswordFormatHandler(InvalidPasswordFormatException ex) {
        return new ResponseError(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(EmailAlreadyInUseException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ResponseError emailAlreadyInUseHandler(EmailAlreadyInUseException ex) {
        return new ResponseError(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    List<ResponseError> argumentNotValidHandler(MethodArgumentNotValidException ex) {
        List<ResponseError> errors = ex.getBindingResult().getAllErrors().stream().map(item -> new ResponseError(item.getDefaultMessage())).collect(Collectors.toList());
        return errors;
    }
}