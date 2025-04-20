package com.exampleapi;

import jakarta.persistence.*;

@Entity
@Table(name="ttt")

public class Employee {

    @Column(name="name",nullable=false)
    private String name;
    @Column(name="salary",nullable=false)
    private int salary;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }
}
