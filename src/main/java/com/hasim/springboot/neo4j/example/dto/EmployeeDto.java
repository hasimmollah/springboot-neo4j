package com.hasim.springboot.neo4j.example.dto;



/**
 * @author Hasim Mollah
 */


public class EmployeeDto extends BaseDto{

    public PositionDto getPosition() {
        return position;
    }

    public void setPosition(PositionDto position) {
        this.position = position;
    }

    public DepartmentDto getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDto department) {
        this.department = department;
    }

    private PositionDto position;

    private DepartmentDto department;


}
