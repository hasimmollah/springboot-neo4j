package com.hasim.springboot.neo4j.example.repository;

import com.hasim.springboot.neo4j.example.entity.Department;
import com.hasim.springboot.neo4j.example.entity.Position;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Hasim Mollah
 */
public interface PositionRepository extends Neo4jRepository<Position, String> {
    @Query(""" 
    MATCH (p:Position)
    WHERE p.name CONTAINS $name RETURN p
    """)
    List<Position> findByName(@Param("name") String name);

}
