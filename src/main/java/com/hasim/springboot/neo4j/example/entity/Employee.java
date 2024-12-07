package com.hasim.springboot.neo4j.example.entity;


import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

/**
 * @author Hasim Mollah
 */

@Node
public class Employee extends BaseEntity{
    @Relationship(type = "WITH_DESIGNATION", direction = Relationship.Direction.OUTGOING)
    private Position position;

   public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }


    @Relationship(type = "IS_PART_OF", direction = Relationship.Direction.OUTGOING)
    private Department department;

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    @Relationship(type = "REPORTS_TO", direction = Relationship.Direction.OUTGOING)
    private Employee manager;

}
