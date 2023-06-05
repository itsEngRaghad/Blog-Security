package com.example.blogsecurity.AdviseController;

import com.example.blogsecurity.APIException.APIException;
import com.example.blogsecurity.APIResponse.APIResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ControllerAdvise {

    @ExceptionHandler(APIException.class)
    public ResponseEntity<APIResponse> APIException(APIException e){
        String message= e.getMessage();
        return ResponseEntity.status(400).body(new APIResponse(message));
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<APIResponse>ArithmeticException(ArithmeticException e){
        String message= e.getMessage();
        return ResponseEntity.status(400).body(new APIResponse(message));
    }

    // Argument Not Valid

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse>MethodArgumentNotValidException(MethodArgumentNotValidException e){
        String message= e.getMessage();
        return ResponseEntity.status(400).body(new APIResponse(message));
    }

    //Mismatch

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<APIResponse>MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
        String message=e.getMessage();
        return ResponseEntity.status(400).body(new APIResponse(message));

    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<APIResponse> DataIntegrityViolationException(DataIntegrityViolationException e){
        String message=e.getMessage();
        return ResponseEntity.status(400).body(new APIResponse(message));
    }

    // Json message not readable
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<APIResponse> HttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String message = e.getMessage();
        return ResponseEntity.status(400).body(new APIResponse(message));
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<APIResponse> TransactionSystemException(TransactionSystemException e) {
        String message = e.getMessage();
        return ResponseEntity.status(400).body(new APIResponse(message));
    }
}
