package com.hasim.springboot.neo4j.example.controller;

import com.hasim.springboot.neo4j.example.dto.DepartmentDto;
import com.hasim.springboot.neo4j.example.dto.EmployeeDetails;
import com.hasim.springboot.neo4j.example.dto.EmployeeDto;
import com.hasim.springboot.neo4j.example.dto.EmployeeRequest;
import com.hasim.springboot.neo4j.example.service.EmployeeFacade;
import com.hasim.springboot.neo4j.example.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Hasim Mollah
 */
@RestController
public class EmployeeController {
    private final EmployeeFacade employeeFacade;
    EmployeeController(EmployeeFacade employeeFacade){
        this.employeeFacade = employeeFacade;
    }

    @GetMapping("/employees/search")
    List<EmployeeDto> search(EmployeeRequest employeeRequest) {
        return employeeFacade.search(employeeRequest);
    }
    @GetMapping("/employees")
    List<EmployeeDetails> employees() {
        return employeeFacade.fetchEmployeeDetails();
    }


    @GetMapping("/employees/{name}")
    List<EmployeeDto> employeesByName(@PathVariable("name") String name) {
        return employeeFacade.findByName(name);
    }

    @PostMapping("/employees/employee")
    EmployeeDto save(@RequestBody EmployeeDto employeeDto) {
        return employeeFacade.save(employeeDto);
    }
}


