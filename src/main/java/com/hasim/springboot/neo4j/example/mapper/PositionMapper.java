package com.hasim.springboot.neo4j.example.mapper;

import com.hasim.springboot.neo4j.example.dto.DepartmentDto;
import com.hasim.springboot.neo4j.example.dto.EmployeeDto;
import com.hasim.springboot.neo4j.example.dto.PositionDto;
import com.hasim.springboot.neo4j.example.entity.Department;
import com.hasim.springboot.neo4j.example.entity.Employee;
import com.hasim.springboot.neo4j.example.entity.Position;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


/**
 * @author Hasim Mollah
 */
@Mapper(componentModel = "spring")
public interface PositionMapper extends BaseMapper{
    @InheritConfiguration(name = "toDto")
    PositionDto toDto(Position position);
    @InheritConfiguration(name = "toEntity")
    Position toEntity(PositionDto dto);
}
