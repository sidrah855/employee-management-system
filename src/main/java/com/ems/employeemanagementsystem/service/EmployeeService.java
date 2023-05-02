package com.ems.employeemanagementsystem.service;

import com.ems.employeemanagementsystem.dto.ApiResponse;
import com.ems.employeemanagementsystem.dto.EmployeeDTO;
import org.springframework.stereotype.Service;


public interface EmployeeService {

    ApiResponse getAllEmployee();

    ApiResponse getEmployeeById(Long employeeId);

    ApiResponse getEmployeeByName(String employeeName);
    ApiResponse saveEmployee(EmployeeDTO employeeDTO);

    ApiResponse getSortedEmployees(String employeeName);
}
