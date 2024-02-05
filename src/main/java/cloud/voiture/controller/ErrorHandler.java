package cloud.voiture.controller;

import java.util.Set;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import cloud.voiture.model.Commission;
import cloud.voiture.model.ResponseWrap;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@ControllerAdvice
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseWrap> handleException(Exception ex) {
        String errorMessage = ex.getMessage();
        if(ex instanceof ConstraintViolationException){
            errorMessage = ((ConstraintViolationException)ex).getErrorMessage();
            System.out.println("makato ve? "+errorMessage);
        }
        ResponseWrap response = ResponseWrap.error(errorMessage);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseWrap> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String errorMessage = ex.getMessage();
        ResponseWrap response = ResponseWrap.error(errorMessage);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    

}