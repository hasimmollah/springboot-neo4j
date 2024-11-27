package com.hasim.springboot.neo4j.example.config;

import com.hasim.springboot.neo4j.example.entity.Employee;
import com.hasim.springboot.neo4j.example.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.Neo4jContainer;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class Neo4jBaseIntegrationTest {

    private static final Neo4jContainer<?> neo4jContainer = new Neo4jContainer<>("neo4j:5.5")
            .withAdminPassword(null); // No password for testing

    @BeforeAll
    static void startNeo4jContainer() {
        neo4jContainer.start();
        System.setProperty("NEO4J_URI", neo4jContainer.getBoltUrl());
    }

    @AfterAll
    static void stopNeo4jContainer() {
        neo4jContainer.stop();
    }

}

