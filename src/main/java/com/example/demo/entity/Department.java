package com.example.demo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="departments")
public class Department {

    @Id
    @Column(name = "dept_no")
    private String departmentId;

    @Column(name = "dept_name")
    private String departmentName;

    @ManyToMany
    @JoinTable(name = "dept_emp",
               joinColumns = { @JoinColumn(name = "dept_no") },
               inverseJoinColumns = { @JoinColumn(name = "emp_no") })
    private List<Employee> employees = new ArrayList<>();

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

}
