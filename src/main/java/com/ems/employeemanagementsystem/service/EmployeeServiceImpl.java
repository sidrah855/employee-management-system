package com.ems.employeemanagementsystem.service;

import com.ems.employeemanagementsystem.dto.ApiResponse;
import com.ems.employeemanagementsystem.dto.EmployeeDTO;
import com.ems.employeemanagementsystem.model.Employee;
import com.ems.employeemanagementsystem.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.ems.employeemanagementsystem.dto.ApiResponse.createResponse;

@Service //this annotation has to be here in impl class else error
@Transactional //when ever we need to delete or modify this annotation is musttttttt you should use @Transactional when you want to execute a series of database operations in a single transaction and rollback the transaction if any of the operations fail.
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ModelMapper modelMapper;

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

       List<Employee> employees=employeeRepository.findByfullName(employeeName);
//        List<Employee> employees=employeeRepository.sortedEmployee();
        if(employees.isEmpty())
        {
            return ApiResponse.builder().status(HttpStatus.NOT_FOUND.value()).message("NO record found ............!").data(null).build();
        }
        return ApiResponse.builder().status(HttpStatus.OK.value()).message("Record Found.......!").data(employees).build();

    }

    @Override
    public ApiResponse saveEmployee(EmployeeDTO employeeDTO) {
        // we always use dto data transfer objects to get data from web and for validation but when we have to store data is database we always map dto's object to entity's then store it in db
        // two-way to map dto to entity 1 manually one by one 2nd using model mapper
        // add model mapper dependency make its object then use it
        // create a bean in main then autowired model mapper so that we don't have to do new modelmapper() everytime
        // model mapper can be use to maps objects having same attributes or difference i.e. property mapping
       Employee employee=modelMapper.map(employeeDTO,Employee.class);
       employee=employeeRepository.save(employee);
       if(employee==null)
       {
           //to make it more short i introduced a create response in api_response class with static import
           return createResponse(HttpStatus.NOT_FOUND.value(),"Could not save data.....!",employee );
           //ApiResponse.builder().status(HttpStatus.NOT_ACCEPTABLE.value()).message("Could not store data in db...!").data(employee).build();

       }
        return createResponse(HttpStatus.OK.value(),"Data saved Successfully.....!",employee );
//     return ApiResponse.builder().status(HttpStatus.OK.value()).message("Data has been saved successfully....!").data(employee).build();
    }

    @Override
    public ApiResponse getSortedEmployees(String employeeName) {

        List<Employee> employees=employeeRepository.sortedEmployee(employeeName);

        return employees.isEmpty()?
                ApiResponse.builder().status(HttpStatus.NOT_FOUND.value()).message("Ops ...").data(employees).build():
                ApiResponse.builder().status(HttpStatus.OK.value()).message("SORTED LIST OF EMPLOYEES...").data(employees).build();
    }

    @Override
    public ApiResponse deleteEmployeeById(Long employeeId) {
        Optional<Employee> employee=employeeRepository.findById(employeeId);
        if(!employee.isPresent())
        {
            return createResponse(HttpStatus.NOT_FOUND.value(), "No recorde found....!",null);
        }
        employeeRepository.deleteById(employeeId);
        return createResponse(HttpStatus.OK.value(), "Data has been removed successfully...!", employeeId);


    }

    @Override
    public ApiResponse deleteByDesignation(String designation) {
        Integer employees =employeeRepository.deleteInBatchByDesignation(designation);
        if(employees.intValue()==0)
        {
            return createResponse(HttpStatus.BAD_GATEWAY.value(), "nothing deleted",employees);
        }
        return createResponse(HttpStatus.OK.value(), "Data has been removed successfully...!", employees);

    }

    @Override
    public ApiResponse deleteEmployee(String department, String designation) {
        Integer result=employeeRepository.deleteEmployee(department,designation);
        if(result.intValue()==0)
        {
            return createResponse(HttpStatus.BAD_GATEWAY.value(), "nothing deleted",result);
        }
        return createResponse(HttpStatus.OK.value(), "Data has been removed successfully...!", result);

    }
}

