package com.hasim.springboot.neo4j.example.controller;

import com.hasim.springboot.neo4j.example.dto.DepartmentDto;
import com.hasim.springboot.neo4j.example.dto.PositionDto;
import com.hasim.springboot.neo4j.example.service.DepartmentService;
import com.hasim.springboot.neo4j.example.service.PositionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hasim Mollah
 */
@RestController
public class PositionController {
    private final PositionService positionService;
    PositionController(PositionService positionService){
        this.positionService = positionService;
    }

    @PostMapping("/positions/position")
    PositionDto save(PositionDto positionDto) {
        return positionService.save(positionDto);
    }
    }
