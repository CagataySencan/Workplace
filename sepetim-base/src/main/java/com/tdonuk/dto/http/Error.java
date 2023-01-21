package com.tdonuk.dto.http;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error {
    private int code;
    private String shortDes;
    private String longDes;
}
