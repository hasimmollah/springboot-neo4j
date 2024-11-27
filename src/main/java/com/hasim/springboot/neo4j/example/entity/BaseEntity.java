package com.hasim.springboot.neo4j.example.entity;


import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;


/**
 * @author Hasim Mollah
 */
@Node
public class BaseEntity {
   public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    private Integer id;
    private String name;
}
