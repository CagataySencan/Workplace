package com.tdonuk.dto.domain;

import lombok.Data;

import java.util.Date;

@Data
public class BaseDTO {
    private String id;
    private Date created;
    private Date lastUpdated;
}
