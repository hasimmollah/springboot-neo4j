package com.hasim.springboot.neo4j.example.controller;

import com.hasim.springboot.neo4j.example.dto.DepartmentDto;
import com.hasim.springboot.neo4j.example.dto.EmployeeDetails;
import com.hasim.springboot.neo4j.example.dto.EmployeeDto;
import com.hasim.springboot.neo4j.example.dto.EmployeeRequest;
import com.hasim.springboot.neo4j.example.service.DepartmentService;
import com.hasim.springboot.neo4j.example.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Hasim Mollah
 */
@RestController
public class DepartmentController {
    private final DepartmentService departmentService;
    DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @PostMapping("/departments/department")
    ResponseEntity<DepartmentDto> save(@RequestBody DepartmentDto departmentDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(departmentService.save(departmentDto));
    }
    }
