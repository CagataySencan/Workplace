package com.tdonuk.sepetim.util;

import com.tdonuk.dto.http.BaseResponse;
import com.tdonuk.dto.http.Error;
import com.tdonuk.exception.BaseException;
import org.springframework.http.HttpStatus;

public class ErrorUtils {
    public static BaseResponse<?> createErrorResponse(Exception e, HttpStatus status) {
        Error error;

        if(e instanceof BaseException customException) error = new Error(customException.getCode(), customException.getShortDesc(), customException.getLongDes());
        else error = new Error(-1, e.getMessage(), null);

        return BaseResponse.fault(error, status.value());
    }

    public static BaseResponse<?> badRequest(Exception e) {
        return createErrorResponse(e,HttpStatus.BAD_REQUEST);
    }

    public static BaseResponse<?> forbidden(Exception e) {
        return createErrorResponse(e,HttpStatus.FORBIDDEN);
    }

    public static BaseResponse<?> unauthorized(Exception e) {
        return createErrorResponse(e,HttpStatus.UNAUTHORIZED);
    }

}
