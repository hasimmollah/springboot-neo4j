package com.hasim.springboot.neo4j.example.controller;

import com.hasim.springboot.neo4j.example.dto.DepartmentDto;
import com.hasim.springboot.neo4j.example.dto.PositionDto;
import com.hasim.springboot.neo4j.example.service.DepartmentService;
import com.hasim.springboot.neo4j.example.service.PositionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    ResponseEntity<PositionDto> save(@RequestBody PositionDto positionDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(positionService.save(positionDto));
    }
    }
