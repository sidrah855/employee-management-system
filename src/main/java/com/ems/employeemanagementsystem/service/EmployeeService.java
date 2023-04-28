package com.ems.employeemanagementsystem.service;

import com.ems.employeemanagementsystem.dto.ApiResponse;
import org.springframework.stereotype.Service;


public interface EmployeeService {

    ApiResponse getAllEmployee();

    ApiResponse getEmployeeById(Long employeeId);

    ApiResponse getEmployeeByName(String employeeName);
}
