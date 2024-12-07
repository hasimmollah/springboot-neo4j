package com.hasim.springboot.neo4j.example.controller;

import com.hasim.springboot.neo4j.example.dto.DepartmentDto;
import com.hasim.springboot.neo4j.example.dto.EmployeeDetails;
import com.hasim.springboot.neo4j.example.dto.EmployeeDto;
import com.hasim.springboot.neo4j.example.dto.EmployeeRequest;
import com.hasim.springboot.neo4j.example.service.DepartmentService;
import com.hasim.springboot.neo4j.example.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
    DepartmentDto save(DepartmentDto departmentDto) {
        return departmentService.save(departmentDto);
    }
    }
