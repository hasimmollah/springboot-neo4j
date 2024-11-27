package com.hasim.springboot.neo4j.example.repository;

import com.hasim.springboot.neo4j.example.entity.Employee;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
/**
 * @author Hasim Mollah
 */
public interface EmployeeRepository extends Neo4jRepository<Employee, Integer> {
    @Query(""" 
    MATCH (e:Employee)-[r3:HAS_MANAGER]->(manager:Employee), 
    (e)-[r1:HAS_POSITION]->(p:Position), 
    (e)-[r2:BELONGS_TO]->(d:Department)
    WHERE manager.name CONTAINS $name RETURN distinct  e, p,d, r1,r2, r3
    """)
    List<Employee> findByManager(@Param("name") String name);

}
