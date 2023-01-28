package com.tdonuk.sepetim.aspect;

import com.tdonuk.dto.http.BaseResponse;
import com.tdonuk.dto.http.Error;
import com.tdonuk.exception.BaseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handle(Exception ex, WebRequest request) {
        if(ex instanceof BaseException e) {
            return handleExceptionInternal(ex, BaseResponse.fault(new Error(e.getCode(), e.getShortDesc(), e.getLongDes()),e.getStatus()), new HttpHeaders(), HttpStatusCode.valueOf(e.getStatus()), request);
        }

        else return handleExceptionInternal(ex, BaseResponse.fault(new Error(0, ex.getMessage(), null), 500), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
