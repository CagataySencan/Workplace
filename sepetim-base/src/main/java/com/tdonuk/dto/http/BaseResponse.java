package com.tdonuk.dto.http;

import com.tdonuk.exception.BaseException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BaseResponse<T>{
    private T data;
    private Error fault;

    private int status;

    public BaseResponse(T data) {
        this.data = data;
    }

    public static <Data> BaseResponse of(Data data) {
        BaseResponse<Data> response = new BaseResponse<>();

        response.setData(data);

        return response;
    }

    public static <Fault extends Error> BaseResponse fault(Fault e, int status) {
        BaseResponse<Data> response = new BaseResponse<>();

        response.setFault(e);
        response.setStatus(status);

        return response;
    }

}
