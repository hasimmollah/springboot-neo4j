package com.hasim.springboot.neo4j.example.repository;

import com.hasim.springboot.neo4j.example.entity.Department;
import com.hasim.springboot.neo4j.example.entity.Employee;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Hasim Mollah
 */
public interface DepartmentRepository extends Neo4jRepository<Department, String> {
    @Query(""" 
    MATCH (d:Department)
    WHERE d.name CONTAINS $name RETURN d
    """)
    List<Department> findByName(@Param("name") String name);

}
