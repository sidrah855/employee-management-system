package com.ems.employeemanagementsystem.dto;
import javax.validation.constraints.NotBlank;

//we can apply validation check on dto object then further process it for that we need to add validation starter dependency
public class EmployeeDTO {

    Long employeeId;

    @NotBlank(message = "Enter name...!") String name;
    String designation;

    //    @Min(value=1,message ="Please write minimum 5 words"
    //    @Length(min=1,max=10)

    String department;
}
