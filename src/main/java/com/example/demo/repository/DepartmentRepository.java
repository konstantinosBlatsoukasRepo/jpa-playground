package com.example.demo.repository;

import com.example.demo.entity.Department;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class DepartmentRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Department findById(String departmentId) {
        return entityManager.find(Department.class, departmentId);
    }

}
