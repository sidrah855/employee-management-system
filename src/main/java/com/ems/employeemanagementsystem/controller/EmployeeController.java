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
        return ResponseEntity.ok(employeeService.getAllEmployee()); //response entity is a class while httpstatus is a enum
    }

    @GetMapping("/get-employee-by-id/{employeeId}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("employeeId") Long employeeId)
    {
        return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
    }
    @GetMapping("/get-employee-by-name/{employeeName}")
    public ResponseEntity<?> getEmployeeByName(@PathVariable("employeeName") String employeeName)
    {
        return ResponseEntity.ok(employeeService.getEmployeeByName(employeeName));
    }

    @PostMapping("/save-employee")   //post mapping handel http post requests
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDTO employeeDTO) //@RequestMapping maps the body of the web request to method parameter
    {
        return ResponseEntity.ok(employeeService.saveEmployee(employeeDTO));
    }
}
