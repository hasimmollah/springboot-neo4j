package com.hasim.springboot.neo4j.example.service;

import com.hasim.springboot.neo4j.example.dao.EmployeeDao;
import com.hasim.springboot.neo4j.example.dto.*;
import com.hasim.springboot.neo4j.example.entity.Employee;
import com.hasim.springboot.neo4j.example.exception.ApplicationException;
import com.hasim.springboot.neo4j.example.mapper.EmployeeMapper;
import com.hasim.springboot.neo4j.example.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.testcontainers.shaded.org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author Hasim Mollah
 */
@Service
@Slf4j
public class EmployeeFacade {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final PositionService positionService;
    EmployeeFacade(EmployeeService employeeService, PositionService positionService, DepartmentService departmentService){
        this.employeeService = employeeService;
        this.positionService = positionService;
        this.departmentService = departmentService;
    }

    public List<EmployeeDto> search(EmployeeRequest employeeRequest){
        return employeeService.search(employeeRequest);
    }

    public List<EmployeeDetails> fetchEmployeeDetails() {
        return employeeService.fetchEmployeeDetails();
    }

    public List<EmployeeDto> findByName(String name){
        return employeeService.findByName(name);
    }

    public EmployeeDto save(EmployeeDto employeeDto) {
        DepartmentDto deptDto = employeeDto.getDepartment();
        PositionDto positionDto = employeeDto.getPosition();
        EmployeeDto manager = employeeDto.getManager();
        deptDto = Optional.ofNullable(deptDto)
                .flatMap(deptDtoVal -> departmentService.byName(deptDtoVal.getName()).stream().findFirst()).orElse(createDepartmentDtoByName(deptDto.getName()));
        positionDto = Optional.ofNullable(positionDto)
                .flatMap(positionDtoVal -> positionService.byName(positionDtoVal.getName()).stream().findFirst()).orElse(createPositionDtoByName(positionDto.getName()));
        manager = Optional.ofNullable(manager)
                .flatMap(managerVal->employeeService.search(managerVal, false).stream().findFirst()).orElse(null);
        employeeDto.setDepartment(deptDto);
        employeeDto.setManager(manager);
        employeeDto.setPosition(positionDto);
        return employeeService.save(employeeDto);
    }

    private DepartmentDto createDepartmentDtoByName(String name){
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName(name);
        return departmentDto;
    }

    private PositionDto createPositionDtoByName(String name){
        PositionDto positionDto = new PositionDto();
        positionDto.setName(name);
        return positionDto;
    }

    private EmployeeDto createEmployeeDtoByName(String name){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName(name);
        return employeeDto;
    }
}
