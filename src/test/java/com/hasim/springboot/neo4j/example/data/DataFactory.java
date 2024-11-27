package com.hasim.springboot.neo4j.example.data;

import com.hasim.springboot.neo4j.example.dto.DepartmentDto;
import com.hasim.springboot.neo4j.example.dto.EmployeeDto;
import com.hasim.springboot.neo4j.example.dto.PositionDto;
import com.hasim.springboot.neo4j.example.entity.Department;
import com.hasim.springboot.neo4j.example.entity.Employee;
import com.hasim.springboot.neo4j.example.entity.Position;

public class DataFactory {
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

    public static EmployeeDto createEmployeeDto(Integer id, String name){
        EmployeeDto emp = new EmployeeDto();
        emp.setId(id);
        emp.setName(name);
        return emp;
    }
    public static EmployeeDto createEmployeeDto(Integer id, String name, Integer deptId, String deptName, Integer posId, String posName){
        EmployeeDto emp =  createEmployeeDto( id,  name);
        emp.setDepartment(createDepartmentDto(deptId, deptName));
        emp.setPosition(createPositionDto(posId, posName));
        return emp;
    }
    public static DepartmentDto createDepartmentDto(Integer id, String name){
        DepartmentDto dept = new DepartmentDto();
        dept.setId(id);
        dept.setName(name);

        return dept;
    }

    public static PositionDto createPositionDto(Integer id, String name){
        PositionDto pos = new PositionDto();
        pos.setId(id);
        pos.setName(name);

        return pos;
    }
}
