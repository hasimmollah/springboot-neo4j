package com.hasim.springboot.neo4j.example.data;

import com.hasim.springboot.neo4j.example.dto.DepartmentDto;
import com.hasim.springboot.neo4j.example.dto.EmployeeDto;
import com.hasim.springboot.neo4j.example.dto.PositionDto;
import com.hasim.springboot.neo4j.example.entity.Department;
import com.hasim.springboot.neo4j.example.entity.Employee;
import com.hasim.springboot.neo4j.example.entity.Position;

import java.util.UUID;

public class DataFactory {
    public static Employee createEmployee(String id, String name){
        Employee emp = new Employee();
        emp.setId(id);
        emp.setName(name);
        return emp;
    }
    public static Employee createEmployee(String id, String name, String deptId, String deptName, String posId, String posName){
        Employee emp =  createEmployee( id,  name);
        emp.setDepartment(createDepartment(deptId, deptName));
        emp.setPosition(createPosition(posId, posName));
        return emp;
    }
    public static Department createDepartment(String id, String name){
        Department dept = new Department();
        dept.setId(id);
        dept.setName(name);

        return dept;
    }

    public static Position createPosition(String id, String name){
        Position pos = new Position();
        pos.setId(id);
        pos.setName(name);

        return pos;
    }

    public static EmployeeDto createEmployeeDto(String id, String name){
        EmployeeDto emp = new EmployeeDto();
        emp.setId(id);
        emp.setName(name);
        return emp;
    }
    public static EmployeeDto createEmployeeDto(String id, String name, String deptId, String deptName, String posId, String posName){
        EmployeeDto emp =  createEmployeeDto( id,  name);
        emp.setDepartment(createDepartmentDto(deptId, deptName));
        emp.setPosition(createPositionDto(posId, posName));
        return emp;
    }
    public static DepartmentDto createDepartmentDto(String id, String name){
        DepartmentDto dept = new DepartmentDto();
        dept.setId(id);
        dept.setName(name);

        return dept;
    }

    public static PositionDto createPositionDto(String id, String name){
        PositionDto pos = new PositionDto();
        pos.setId(id);
        pos.setName(name);

        return pos;
    }
}
