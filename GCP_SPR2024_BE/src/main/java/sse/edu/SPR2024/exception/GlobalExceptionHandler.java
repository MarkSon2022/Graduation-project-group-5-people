package sse.edu.SPR2024.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sse.edu.SPR2024.dto.ErrorDetail;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

    //specific exception handler
    //This exception is thrown when use specific exception:
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorDetail> handlerCustomException(CustomException exception, WebRequest webRequest) {

        //set error details for exception
        ErrorDetail errorDetail = new ErrorDetail(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
        // return exception with right status
        return new ResponseEntity<>(errorDetail, exception.getStatus());
    }

    // global exception handler
    //This exception is thrown when not use other specific exception:
    @ExceptionHandler(Exception.class)
    public  ResponseEntity<ErrorDetail> handlerGlobalException(Exception exception, WebRequest webRequest){
        //set error details for exception
        ErrorDetail errorDetail= new ErrorDetail(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
        //return global exception with status
        return  new ResponseEntity<>(errorDetail,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // valid exception handler
    //This exception is thrown when an argument annotated with @Valid failed validation:
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        //get errors from MethodArgumentNotValidException
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        //
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }




}
