package com.spring.HospitalDemo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserFoundException {

  @ExceptionHandler()
  public String handlerUserFoundException(Exception ex) {
    return "redirect:/signIn?error";
  }
}
