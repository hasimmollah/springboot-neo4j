package com.hasim.springboot.neo4j.example.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class EmployeeRequest {

    String manager;

    String dept;
}
