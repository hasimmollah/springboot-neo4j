package com.hasim.springboot.neo4j.example.service;

import com.hasim.springboot.neo4j.example.dao.EmployeeDao;
import com.hasim.springboot.neo4j.example.dto.EmployeeDto;
import com.hasim.springboot.neo4j.example.dto.EmployeeRequest;
import com.hasim.springboot.neo4j.example.entity.Employee;
import com.hasim.springboot.neo4j.example.mapper.EmployeeMapper;
import com.hasim.springboot.neo4j.example.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static com.hasim.springboot.neo4j.example.data.DataFactory.createEmployee;
import static com.hasim.springboot.neo4j.example.data.DataFactory.createEmployeeDto;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private EmployeeMapper employeeMapper;
    @Mock
    private EmployeeDao employeeDao;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);
    }

    static Stream<Arguments> testParamForShouldVerifySearch(){
        EmployeeRequest employeeRequest = new EmployeeRequest();
        EmployeeRequest employeeRequestWithManager = new EmployeeRequest();
        employeeRequestWithManager.setManager("Abc");
        EmployeeRequest employeeRequestWithDept = new EmployeeRequest();
        employeeRequestWithDept.setDept("IT");
        String empId = UUID.randomUUID().toString();
        String empName = "abc";
        String deptId =UUID.randomUUID().toString();
        String deptName = "IT";
        String posId = UUID.randomUUID().toString();
        String posName = "Dev";

        List<Employee> employees = List.of(createEmployee(empId, empName, deptId, deptName, posId, posName));
        List<EmployeeDto> employeeDtoList = List.of(createEmployeeDto(empId, empName, deptId, deptName, posId, posName));

        return Stream.of(
                Arguments.of(employeeRequestWithManager, employees, employeeDtoList),
                Arguments.of(employeeRequest, employees, employeeDtoList),
                Arguments.of(employeeRequestWithDept, employees, employeeDtoList)
        );
    }
    @ParameterizedTest
    @MethodSource("testParamForShouldVerifySearch")
    void shouldVerifySearch(EmployeeRequest employeeRequest, List<Employee> employees, List<EmployeeDto> employeeDtoListExpected){
        when(employeeRepository.findByManager(anyString())).thenReturn(employees);
        when(employeeRepository.findAll()).thenReturn(employees);
        when(employeeDao.findEmployeesByDepartment(anyString())).thenReturn(employees);
        for(int i =0; i< employees.size(); i++){
            when(employeeMapper.toDto(employees.get(i))).thenReturn(employeeDtoListExpected.get(i));
        }

        List<EmployeeDto> employeeDtoActualList = employeeService.search(employeeRequest);
        assertEquals(employeeDtoListExpected.size(), employeeDtoActualList.size());


    }

}
