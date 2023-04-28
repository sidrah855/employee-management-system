package com.ems.employeemanagementsystem.dto;

import lombok.Builder;
import lombok.Data;

//it is considered a good practice to return detail response
@Builder
@Data
public class ApiResponse {
    private int status;
    private String message;
    private Object data;
}
