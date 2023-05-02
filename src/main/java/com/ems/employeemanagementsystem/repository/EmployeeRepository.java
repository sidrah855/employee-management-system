package com.ems.employeemanagementsystem.repository;

import com.ems.employeemanagementsystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    //two ways 1 by method name 2 by using manually defined query
    /*
    * 1. method name according to naming convention findBy followed by attribute name in camelcase
    * its equivalent to select * from employee where full_name=employeeName
    * Parsing query method names is divided into subject and predicate
    * findBy ExistsBy is the subject
    *
    * */
    List<Employee> findByfullName(String employeeName) ;
    /*
    2. Using manually defined query
    jpa will bind the @query with method and then return the results
    JPQL query works according to classes or attribute defined in project rather than table and attribute of database
     e is alies
     */
    @Query("Select e from Employee e order by fullName")
    List<Employee> sortedEmployee();

    /*
    native query we can perform native queries as well
    @Query(
            value="SELECT TOP (1000) [student_id]\n" +
                    "      ,[email_id]\n" +
                    "      ,[first_name]\n" +
                    "      ,[email_address]\n" +
                    "      ,[guardian_mobile]\n" +
                    "      ,[guardian_name]\n" +
                    "      ,[last_name]\n" +
                    "  FROM [practice].[dbo].[table_student]",
            nativeQuery = true
    )
     */


}
