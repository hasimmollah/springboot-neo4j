package com.hasim.springboot.neo4j.example.repository;

import com.hasim.springboot.neo4j.example.data.EntityFactory;
import com.hasim.springboot.neo4j.example.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataNeo4jTest
public class EmployeeRepositoryIntegrationTest {


    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void testSaveAndRetrieveEmployee() {
        // Save an employee
        String managerName = "Mike";
        String name = "Alice";
        String posId = UUID.randomUUID().toString();
        Employee employee = EntityFactory.createEmployee(UUID.randomUUID().toString(),name,UUID.randomUUID().toString(),"SW",posId,"IT");
        Employee manager =EntityFactory.createEmployee(UUID.randomUUID().toString(),managerName,UUID.randomUUID().toString(),"MGR",posId,"IT");
        employee.setManager(manager);
        employeeRepository.save(employee);

        // Retrieve the employee
        List<Employee> retrievedEmployeeList = employeeRepository.findByManager(managerName);

        // Assertions
        assertThat(retrievedEmployeeList).isNotNull().size().isEqualTo(1);
        assertThat(retrievedEmployeeList.get(0).getName()).isEqualTo(name);
    }

}
