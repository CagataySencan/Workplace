package com.tdonuk.dto.http;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BaseResponse<T>{
    private T data;
    private int status;

    public BaseResponse(T data) {
        this.data = data;
    }

    public static <Data> BaseResponse of(Data data, int status) {
        BaseResponse<Data> response = new BaseResponse<>();
        response.setData(data);
        response.setStatus(status);

        return response;
    }

}
