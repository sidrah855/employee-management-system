package com.ems.employeemanagementsystem.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

//we can apply validation check on dto object then further process it for that we need to add validation starter dependency
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Long employeeId;

    @NotBlank(message = "Enter name...!")
    private String fullName;

    private String designation;

    //    @Min(value=1,message ="Please write minimum 5 words"
    //    @Length(min=1,max=10)

    private String department;
}
