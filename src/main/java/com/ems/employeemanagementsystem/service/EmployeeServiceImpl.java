package com.ems.employeemanagementsystem.service;

import com.ems.employeemanagementsystem.dto.ApiResponse;
import com.ems.employeemanagementsystem.model.Employee;
import com.ems.employeemanagementsystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //this annotation has to be here in impl class else error
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public ApiResponse getAllEmployee() {

        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            return ApiResponse.builder().status(HttpStatus.NOT_FOUND.value()).message("No Employees Record exists......!").data(employees).build();
        }
        return ApiResponse.builder().status(HttpStatus.OK.value()).message("Records of all the employee.....!").data(employeeRepository.findAll()).build();

    }

    @Override
    public ApiResponse getEmployeeById(Long employeeId) {
        //if your primary key is of simple type that is integer long then you dont have to define findbyid in repository we can use default one's
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (!employee.isPresent()) {
            return ApiResponse.builder().status(HttpStatus.NOT_FOUND.value()).message("No Record Found......!").data(employee).build();
        }
        return ApiResponse.builder().status(HttpStatus.OK.value()).message("Record Found.......!").data(employee).build();
    }

    @Override
    public ApiResponse getEmployeeByName(String employeeName) {

     //  List<Employee> employees=employeeRepository.findByfullName(employeeName);
        List<Employee> employees=employeeRepository.sortedEmployee();
        System.out.println("    ");
        return ApiResponse.builder().status(HttpStatus.OK.value()).message("Record Found.......!").data(employees).build();

    }
}

