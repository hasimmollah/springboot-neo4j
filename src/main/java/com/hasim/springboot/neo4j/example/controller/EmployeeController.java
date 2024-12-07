package com.hasim.springboot.neo4j.example.controller;

import com.hasim.springboot.neo4j.example.dto.EmployeeDetails;
import com.hasim.springboot.neo4j.example.dto.EmployeeDto;
import com.hasim.springboot.neo4j.example.dto.EmployeeRequest;
import com.hasim.springboot.neo4j.example.service.EmployeeFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    ResponseEntity<List<EmployeeDto>> search(EmployeeRequest employeeRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeFacade.search(employeeRequest));
    }
    @GetMapping("/employees")
    ResponseEntity<List<EmployeeDetails>> employees() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeFacade.fetchEmployeeDetails());
    }


    @GetMapping("/employees/employee/{name}")
    ResponseEntity<List<EmployeeDto>> employeesByName(@PathVariable("name") String name) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeFacade.findByName(name));
    }

    @PostMapping("/employees/employee")
    ResponseEntity<EmployeeDto> save(@RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeFacade.save(employeeDto));
    }
}


