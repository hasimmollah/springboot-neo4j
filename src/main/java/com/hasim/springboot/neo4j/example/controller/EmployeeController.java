package com.hasim.springboot.neo4j.example.controller;

import com.hasim.springboot.neo4j.example.dto.EmployeeDetails;
import com.hasim.springboot.neo4j.example.dto.EmployeeDto;
import com.hasim.springboot.neo4j.example.dto.EmployeeRequest;
import com.hasim.springboot.neo4j.example.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Hasim Mollah
 */
@RestController
public class EmployeeController {
    private final EmployeeService employeeService;
    EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/employees/search")
    List<EmployeeDto> search(EmployeeRequest employeeRequest) {
        return employeeService.search(employeeRequest);
    }
    @GetMapping("/employees")
    List<EmployeeDetails> employees() {
        return employeeService.fetchEmployeeDetails();
    }


    @GetMapping("/employees/{name}")
    List<EmployeeDto> employeesByName(@PathVariable("name") String name) {
        return employeeService.findByName(name);
    }
}
