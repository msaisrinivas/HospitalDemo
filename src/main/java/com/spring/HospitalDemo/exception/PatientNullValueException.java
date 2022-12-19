package com.spring.HospitalDemo.exception;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PatientNullValueException {

  @ExceptionHandler(BindException.class)
  public String handleValidationExceptions(BindException ex) {
    return "redirect:/patients/addform?error";
  }
}
