package com.hasim.springboot.neo4j.example.mapper;

import com.hasim.springboot.neo4j.example.entity.Department;
import com.hasim.springboot.neo4j.example.entity.Employee;
import com.hasim.springboot.neo4j.example.entity.Position;
import com.hasim.springboot.neo4j.example.dto.DepartmentDto;
import com.hasim.springboot.neo4j.example.dto.EmployeeDto;
import com.hasim.springboot.neo4j.example.dto.PositionDto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


/**
 * @author Hasim Mollah
 */
@Mapper(componentModel = "spring")
public interface EmployeeMapper extends BaseMapper{
    @InheritConfiguration(name = "toDto")
    @Mapping(target = "position", source = "position")
    @Mapping(target = "department", source = "department")
    @Mapping(target = "manager", source = "manager", qualifiedByName = "toDtoManagerMapping")
    EmployeeDto toDto(Employee employee);
    PositionDto toPosition(Position position);
    DepartmentDto toDepartment(Department department);


    @InheritConfiguration(name = "toEntity")
    @Mapping(target = "position", source = "position")
    @Mapping(target = "department", source = "department")
    @Mapping(target = "manager", source = "manager", qualifiedByName = "toEntityManagerMapping")
    Employee toEntity(EmployeeDto employeeDto);
    Position toPosition(PositionDto positionDto);
    Department toDepartment(DepartmentDto departmentDto);



    @Named("toDtoManagerMapping")
    EmployeeDto toDtoManagerMapping(Employee manager);

    @Named("toEntityManagerMapping")
    Employee toEntityManagerMapping(EmployeeDto manager);
}

