package com.hasim.springboot.neo4j.example.service;

import com.hasim.springboot.neo4j.example.dto.DepartmentDto;
import com.hasim.springboot.neo4j.example.dto.PositionDto;
import com.hasim.springboot.neo4j.example.mapper.DepartmentMapper;
import com.hasim.springboot.neo4j.example.mapper.PositionMapper;
import com.hasim.springboot.neo4j.example.repository.DepartmentRepository;
import com.hasim.springboot.neo4j.example.repository.PositionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Hasim Mollah
 */
@Service
@Slf4j
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    DepartmentService(DepartmentRepository departmentRepository,
                      DepartmentMapper departmentMapper){
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    public List<DepartmentDto> byName(String name){
        return departmentRepository.findByName(name).stream().map(position->departmentMapper.toDto(position)).toList();
    }

    public List<DepartmentDto> search(String name){
        return departmentRepository.findByName(name).stream().map(position->departmentMapper.toDto(position)).toList();
    }

    public DepartmentDto save(DepartmentDto departmentDto){
        return departmentMapper.toDto(departmentRepository.save(departmentMapper.toEntity(departmentDto)));
    }

}
