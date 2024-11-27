package com.hasim.springboot.neo4j.example.dao;

import com.hasim.springboot.neo4j.example.config.Neo4jBaseIntegrationTest;
import com.hasim.springboot.neo4j.example.dao.EmployeeDao;
import com.hasim.springboot.neo4j.example.data.EntityFactory;
import com.hasim.springboot.neo4j.example.dto.EmployeeDetails;
import com.hasim.springboot.neo4j.example.entity.Employee;
import com.hasim.springboot.neo4j.example.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;


public class EmployeeDaoIntegrationTest extends Neo4jBaseIntegrationTest {
    String MANAGER_NAME = "Mike";
    String EMP_NAME = "Alice";
    String DEPT = "IT";
    String EMP_POS = "SW";
    String MGR_POS = "MGR";
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeDao employeeDao;

    @BeforeEach
    protected void setup() {

        //save employee
        Employee employee = EntityFactory.createEmployee(1, EMP_NAME,1,DEPT,1,EMP_POS);
        Employee manager =EntityFactory.createEmployee(2, MANAGER_NAME,1,DEPT,2,MGR_POS);
        employee.setManager(manager);
        employeeRepository.save(employee);
    }


    @Test
    void shouldVerifyFetchEmployeeDetails() {

        // Retrieve the employee
        List<EmployeeDetails> retrievedEmployeeList = employeeDao.fetchEmployeeDetails();

        // Assertions
        assertThat(retrievedEmployeeList).isNotNull().size().isEqualTo(2);
        boolean containsMatch = retrievedEmployeeList.stream().anyMatch(e -> e.name().equalsIgnoreCase(EMP_NAME));

        assertTrue(containsMatch);
    }

    @Test
    void shouldVerifyFindEmployeesByName() {

        // Retrieve the employee
        List<Employee> retrievedEmployeeList = employeeDao.findEmployeesByName(EMP_NAME);

        // Assertions
        assertThat(retrievedEmployeeList).isNotNull().size().isEqualTo(1);
        assertThat(retrievedEmployeeList.get(0).getName()).isEqualTo(EMP_NAME);
    }
    @Test
    void shouldVerifyFindEmployeesByDepartment() {

        // Retrieve the employee
        List<Employee> retrievedEmployeeList = employeeDao.findEmployeesByDepartment(DEPT);

        // Assertions
        assertThat(retrievedEmployeeList).isNotNull().size().isEqualTo(2);
        assertThat(retrievedEmployeeList.get(0).getDepartment().getName()).isEqualTo(DEPT);
    }

}
