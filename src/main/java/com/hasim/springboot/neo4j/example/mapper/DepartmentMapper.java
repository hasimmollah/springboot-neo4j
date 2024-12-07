package com.hasim.springboot.neo4j.example.mapper;

import com.hasim.springboot.neo4j.example.dto.DepartmentDto;
import com.hasim.springboot.neo4j.example.dto.PositionDto;
import com.hasim.springboot.neo4j.example.entity.Department;
import com.hasim.springboot.neo4j.example.entity.Position;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;


/**
 * @author Hasim Mollah
 */
@Mapper(componentModel = "spring")
public interface DepartmentMapper extends BaseMapper{
    @InheritConfiguration(name = "toDto")
    DepartmentDto toDto(Department position);
    @InheritConfiguration(name = "toEntity")
    Department toEntity(DepartmentDto dto);
}
