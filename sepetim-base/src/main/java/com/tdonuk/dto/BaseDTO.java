package com.tdonuk.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BaseDTO {
    protected String id;
    protected Date created;
    protected Date lastUpdated;
}
