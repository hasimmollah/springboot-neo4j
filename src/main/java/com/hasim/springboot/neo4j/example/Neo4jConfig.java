package com.hasim.springboot.neo4j.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Configuration
public class Neo4jConfig {

    @Bean
    public UUIDStringGenerator uuidStringGenerator() {
        return new UUIDStringGenerator();
    }
}
