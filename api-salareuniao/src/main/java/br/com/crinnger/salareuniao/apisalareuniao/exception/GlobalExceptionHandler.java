package br.com.crinnger.salareuniao.apisalareuniao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
        return new ResponseEntity<ErrorDetails>(ErrorDetails
                .builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .detail(request.getDescription(false))
                .build()
                , HttpStatus.NOT_FOUND
        );
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> resourceNotFoundException(Exception ex, WebRequest request){
        return new ResponseEntity<ErrorDetails>(ErrorDetails
                .builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .detail(request.getDescription(false))
                .build()
                , HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
