package com.shaikhabdulgani.crudrest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

    private int statusCode;
    private String type;
    private Date date;
    private String message;

}
