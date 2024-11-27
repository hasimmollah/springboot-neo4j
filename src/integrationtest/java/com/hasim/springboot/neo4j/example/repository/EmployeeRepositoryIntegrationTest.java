package com.hasim.springboot.neo4j.example.repository;

import com.hasim.springboot.neo4j.example.data.EntityFactory;
import com.hasim.springboot.neo4j.example.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;

import java.util.List;

import static com.hasim.springboot.neo4j.example.data.EntityFactory.createEmployee;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@DataNeo4jTest
public class EmployeeRepositoryIntegrationTest {


    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void testSaveAndRetrieveEmployee() {
        // Save an employee
        String managerName = "Mike";
        String name = "Alice";
        Employee employee = EntityFactory.createEmployee(1,name,1,"SW",1,"IT");
        Employee manager =EntityFactory.createEmployee(2,managerName,2,"MGR",1,"IT");
        employee.setManager(manager);
        employeeRepository.save(employee);

        // Retrieve the employee
        List<Employee> retrievedEmployeeList = employeeRepository.findByManager(managerName);

        // Assertions
        assertThat(retrievedEmployeeList).isNotNull().size().isEqualTo(1);
        assertThat(retrievedEmployeeList.get(0).getName()).isEqualTo(name);
    }

}
