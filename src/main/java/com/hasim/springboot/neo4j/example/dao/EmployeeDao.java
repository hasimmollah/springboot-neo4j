package com.hasim.springboot.neo4j.example.dao;

import com.hasim.springboot.neo4j.example.dto.EmployeeDetails;
import com.hasim.springboot.neo4j.example.entity.Employee;
import org.neo4j.cypherdsl.core.*;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.neo4j.cypherdsl.core.Cypher.*;

/**
 * @author Hasim Mollah
 */
@Component
public class EmployeeDao {
    private final Neo4jClient neo4jClient;
    private final Neo4jTemplate neo4jTemplate;

    public EmployeeDao(Neo4jClient neo4jClient, Neo4jTemplate neo4jTemplate) {
        this.neo4jClient = neo4jClient;
        this.neo4jTemplate = neo4jTemplate;
    }

    public List<Employee> findEmployeesByDepartment(String department) {;
        Node emp = node("Employee");
        Node dept = node("Department").withProperties("name", parameter("department"));
        NamedPath path = Cypher.path("r").definedBy(emp.relationshipTo(dept).unbounded());
        Cypher.node("Employee").named("emp").relationshipTo(node("Position").named("pos"), "BELONGS_TO").named("r");
        SymbolicName p = path.getRequiredSymbolicName();

        Statement statement =Cypher.match(
                node("Employee").named("emp")
                        .relationshipTo(
                                node("Department").named("dept")
                                        .withProperties("name", parameter("department")), "BELONGS_TO").named("r")
                , anyNode("emp")
                        .relationshipTo(
                                node("Position").named("pos"), "HAS_POSITION").named("r1"))
                .returningDistinct(name("emp"), name("dept"), name("r"), name("pos"), name("r1")).build();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("department", department);
        return neo4jTemplate.findAll(statement, parameters, Employee.class);
    }

    public List<Employee> findEmployeesByName(String name) {
        String query = """ 
    MATCH (e:Employee)-[r1:HAS_POSITION]->(p:Position), 
    (e)-[r2:BELONGS_TO]->(d:Department)
    WHERE e.name CONTAINS $name RETURN distinct  e, p,d, r1,r2
    """;

        return neo4jTemplate.findAll(
                query,
                Map.of("name", name),
                Employee.class
        );
    }

    public List<EmployeeDetails> fetchEmployeeDetails() {
        return neo4jClient.query("""
                MATCH (e:Employee)-[:HAS_POSITION]->(p:Position),
                      (e)-[:BELONGS_TO]->(d:Department)
                      
                OPTIONAL MATCH (e)-[:HAS_MANAGER]->(m:Employee), (m)-[:HAS_POSITION]->(mp:Position),
                               (m)-[:BELONGS_TO]->(md:Department)
                RETURN e.id as empId, e.name as empName, p.name as empPos, d.name as empDept, m.id as mgrId, m.name as mgrName, mp.name as mgrPos, md.name as mgrDept
                """)
                .fetchAs(EmployeeDetails.class)
                .mappedBy((typeSystem, record) -> {
                    EmployeeDetails manager = null;

                    if (record.containsKey("mgrId") && !record.get("mgrId").isNull() ) {
                        manager = new EmployeeDetails(record.get("mgrId").asInt(),record.get("mgrName").asString(), null, record.get("mgrPos").asString(),record.get("mgrDept").asString());

                    }
                    EmployeeDetails employeeDetails = new EmployeeDetails(record.get("empId").asInt(),record.get("empName").asString(), manager, record.get("empPos").asString(),record.get("empDept").asString());


                    return employeeDetails;
                })
                .all().stream().toList();
    }
}
