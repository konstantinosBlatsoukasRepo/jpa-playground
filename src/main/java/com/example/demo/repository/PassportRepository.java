package com.example.demo.repository;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Passport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;

@Repository
@Transactional
public class PassportRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Passport findById(int id) {
        // employee is fetched as well (eagerly fetched)
        // left join is performed
        Passport passport = entityManager.find(Passport.class, id);
        return passport;
    }

    public void savePassportWithEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("Gio");
        employee.setLastName("vanni");
        employee.setGender("M");
        employee.setHireDate(new Date());
        employee.setBirthDate(new Date());
        entityManager.persist(employee);

        // first you have to persist the dependency! e.g. the employee
        Passport passport = new Passport();
        passport.setNumber("AAA123");
        passport.setEmployee(employee);
        entityManager.persist(passport);
    }

}
