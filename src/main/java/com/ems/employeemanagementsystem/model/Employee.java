package com.ems.employeemanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

// PLAIN OLD JAVA OBJECT (POJO) as an entity 
//@Entity //we need to add jpa dependency else we cannot use database related annotation such as id column table
@Builder //part of lombok Allows method chaining and build complex object without following traditional new approach
@AllArgsConstructor
@NoArgsConstructor
@Data //part of lombok dependency no need of getter and setters
@Entity //after this if uou miss id annotation it will give error
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeId;
    @Column(name="fullname",length = 30)
    private String fullName;
    @Column
    private String designation;
    @Column
    private String department;
}
