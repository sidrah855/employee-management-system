package com.ems.employeemanagementsystem.controller;

import com.ems.employeemanagementsystem.dto.ApiResponse;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {
    //simple response return
    @GetMapping("/using-string")
    public String welcome()
    {
        return "IT'S A SIMPLE RESPONSE RETURN USING STRING RETURN TYPE......!";
    }

    //using response entity with just status code
    @GetMapping("/using-response-entity")
    public ResponseEntity<?> welcome1(){
        return new ResponseEntity<>("using response entity boday and status", HttpStatus.OK);
    }
    @GetMapping("/response-entity-ok")
    public ResponseEntity<?> welcome2(){
        return  ResponseEntity.ok("Using static method of response entity");
    }
    @GetMapping("/apiResponse")
    public ApiResponse welcome3(){
        return ApiResponse.builder().status(HttpStatus.OK.value()).message("USING API RESPONSE AS RETURN TYPE...!").data(null).build();
    }
    @GetMapping("/Response-entity-and-apiResponse")
    public ResponseEntity<?> welcome4()
    {
        //this is the best approch to return data that is using a response entiy inside using api response for our own ease
        return ResponseEntity.ok(ApiResponse.builder().status(HttpStatus.OK.value()).message("RESPONSE ENTITY AS RETURN TYPE AND API RESPONSE OBJECT").data(null).build());

    }


}
