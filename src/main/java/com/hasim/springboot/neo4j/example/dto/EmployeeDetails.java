package com.hasim.springboot.neo4j.example.dto;


/**
 * @author Hasim Mollah
 */
public record EmployeeDetails (int id, String name, EmployeeDetails manager, String designation, String department) {
}
