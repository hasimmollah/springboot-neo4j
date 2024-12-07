package com.hasim.springboot.neo4j.example.dto;


import java.util.UUID;

/**
 * @author Hasim Mollah
 */

public class BaseDto {
   public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String id;
    private String name;
}
