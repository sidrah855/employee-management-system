package com.ems.employeemanagementsystem.controller;


import com.ems.employeemanagementsystem.dto.EmployeeDTO;
import com.ems.employeemanagementsystem.service.EmployeeService;
import com.ems.employeemanagementsystem.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Controller is responsible only for request and response or dto objects validation or all the thing related to HTTP REQUESTS
@RestController
//The mapping works only when we add rest-controller annotation with controller and without it is doesn't work
        //in spring boot by default all the methods return json response
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Deprecated //not used anymore
    public ResponseEntity<?> test() {
        return ResponseEntity.ok(HttpStatus.OK); //response entity is a class while http status is a enum
    }

    @GetMapping("/get-all-employee") // /get-all-employee it called uri or endpoint
    public ResponseEntity<?> getAllEmployee() {
        // in this we are using default repository method
        return ResponseEntity.ok(employeeService.getAllEmployee()); //response entity is a class while httpstatus is a enum
    }

    @GetMapping("/get-employee-by-id/{employeeId}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("employeeId") Long employeeId)
    {
        //if your primary key is of simple type that is integer long then you dont have to define findbyid in repository we can use default one's
        return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
    }
    @GetMapping("/get-employee-by-name/{employeeName}")
    public ResponseEntity<?> getEmployeeByName(@PathVariable("employeeName") String employeeName)
    { //in this we are using naming convention method of jpa
        return ResponseEntity.ok(employeeService.getEmployeeByName(employeeName));
    }

    @PostMapping("/save-employee")   //post mapping handel http post requests
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDTO employeeDTO) //@RequestMapping maps the body of the web request to method parameter
    {   // simple save method of jpa and model mapper and utility method of createResponse
        return ResponseEntity.ok(employeeService.saveEmployee(employeeDTO));
    }
    @GetMapping("/get-sorted-employee-by-name/{employeeName}")
    public ResponseEntity<?> getSortedEmployees(@PathVariable("employeeName") String employeeName)
    {
        //in this we are using @query annotation and custom jpql and ternary operator
        return ResponseEntity.ok(employeeService.getSortedEmployees(employeeName));
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllEmployees()
    {
        //jpa doesnot provide any delete all method we have to use @query or other means

        return  ResponseEntity.ok(employeeService.deleteAllEmployees());
    }

     // put and patch both use for update only difference is that put is used when we want to modify whole data in database we have to send whole object put and post used interchangeably
    //  patch when we want to update some part of data

}
