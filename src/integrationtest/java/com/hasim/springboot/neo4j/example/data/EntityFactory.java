package com.hasim.springboot.neo4j.example.data;

import com.hasim.springboot.neo4j.example.entity.Department;
import com.hasim.springboot.neo4j.example.entity.Employee;
import com.hasim.springboot.neo4j.example.entity.Position;

public class EntityFactory {
    public static Employee createEmployee(Integer id, String name){
        Employee emp = new Employee();
        emp.setId(id);
        emp.setName(name);
        return emp;
    }
    public static Employee createEmployee(Integer id, String name, Integer deptId, String deptName, Integer posId, String posName){
        Employee emp =  createEmployee( id,  name);
        emp.setDepartment(createDepartment(deptId, deptName));
        emp.setPosition(createPosition(posId, posName));
        return emp;
    }
    public static Department createDepartment(Integer id, String name){
        Department dept = new Department();
        dept.setId(id);
        dept.setName(name);

        return dept;
    }

    public static Position createPosition(Integer id, String name){
        Position pos = new Position();
        pos.setId(id);
        pos.setName(name);

        return pos;
    }
}
