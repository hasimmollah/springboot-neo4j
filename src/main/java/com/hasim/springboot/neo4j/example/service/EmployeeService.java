package com.hasim.springboot.neo4j.example.service;

import com.hasim.springboot.neo4j.example.dto.EmployeeDetails;
import com.hasim.springboot.neo4j.example.entity.Employee;
import com.hasim.springboot.neo4j.example.exception.ApplicationException;
import com.hasim.springboot.neo4j.example.repository.EmployeeRepository;
import com.hasim.springboot.neo4j.example.dao.EmployeeDao;
import com.hasim.springboot.neo4j.example.dto.EmployeeDto;
import com.hasim.springboot.neo4j.example.dto.EmployeeRequest;
import com.hasim.springboot.neo4j.example.mapper.EmployeeMapper;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.testcontainers.shaded.org.apache.commons.lang3.StringUtils;


import java.util.ArrayList;
import java.util.List;
/**
 * @author Hasim Mollah
 */
@Service
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final EmployeeDao employeeDao;
    EmployeeService(EmployeeRepository employeeRepository,
                    EmployeeMapper employeeMapper,
                    EmployeeDao employeeDao){
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.employeeDao = employeeDao;
    }

    public List<EmployeeDto> search(EmployeeRequest employeeRequest){
        List<EmployeeDto> result;
        String manager = employeeRequest.getManager();
        String dept = employeeRequest.getDept();
        if(StringUtils.isNotBlank(manager)){
            log.debug(String.format("Fetching Employees for manager %s .", manager));
            result = employeeRepository.findByManager(manager).stream().map(employee->employeeMapper.toDto(employee)).toList();
        } else if (StringUtils.isNotBlank(dept)){
            log.debug(String.format("Fetching Employees for dept %s .", dept));
            result = employeeDao.findEmployeesByDepartment(dept).stream().map(employee->employeeMapper.toDto(employee)).toList();
        } else {
            log.debug(String.format("Fetching all Employees"));
            result = employeeRepository.findAll().stream().map(employee->employeeMapper.toDto(employee)).toList();
        }
        return result;
    }

    public List<EmployeeDetails> fetchEmployeeDetails() {
        return employeeDao.fetchEmployeeDetails();
    }

    public List<EmployeeDto> findByName(String name){
        List<Employee> employeeList = employeeDao.findEmployeesByName(name);
        if(employeeList.size()==0){
            throw new ApplicationException(String.format("No Employees with name %s .", name));
        }
        return employeeList.stream().map(employee->employeeMapper.toDto(employee)).toList();
    }
}
