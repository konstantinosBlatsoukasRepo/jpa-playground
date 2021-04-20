package com.example.demo.repository;

import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Hobbies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
//@Transactional
public class EmployeeRepository {

    // connect to DB
    // EntityManager: manages the entities, is an interface to PersistenceContext
    @PersistenceContext
    EntityManager entityManager;

    public Employee findById(int employeeId) {
        Employee employee = entityManager.find(Employee.class, employeeId);
        return employee;
    }

    @Autowired
    DepartmentRepository departmentRepository;

    public Employee update(Employee employee) {
        Employee updatedEmployee = entityManager.merge(employee);
        return updatedEmployee;
    }

    public Employee insert(Employee employee) {
        Employee updatedEmployee = entityManager.merge(employee);
        return updatedEmployee;
    }

    public Employee save(Employee employee) {
        entityManager.getTransaction().begin();
//        if (employee.getEmployeeId() == 0) {
            entityManager.persist(employee);
//            entityManager.getTransaction().commit();
//        } else {
//            entityManager.merge(employee);
//        }
        return employee;
    }

    public void deleteById(int  employeeId) {
        Employee employee = entityManager.find(Employee.class, employeeId);
        entityManager.remove(employee);
    }

    public void playWithEntityManger(int employeeId) {
        Employee employee = entityManager.find(Employee.class, employeeId);
        employee.setFirstName("Mitsos");
    }

    public void playWithEntityMangerDetach(int employeeId) {
        Employee employee = entityManager.find(Employee.class, employeeId);
        // after detaching the set method has no effect in DB
        // e.g. update is not performed
        // or you can use clear to remove everything that used to be tracked
        entityManager.detach(employee);
        employee.setFirstName("Vaggos");
    }

    public void playWithEntityManagerRefresh(int employeeId) {
        Employee employee = entityManager.find(Employee.class, employeeId);

        employee.setFirstName("Gavalaz");

        entityManager.flush();
        // after the refresh, the value of the first name must be Vaggos
        entityManager.refresh(employee);

        System.out.println(employee.getFirstName());
    }

    public List<Employee> getAllEmployeesJpql() {
        List<Employee> employees = entityManager.createQuery("Select e From Employee e").getResultList();
        return employees;
    }

    public List<Employee> getAllEmployeesTypedJpql() {
        TypedQuery<Employee> employeesQuery = entityManager.createQuery("Select e From Employee e", Employee.class);
        List<Employee> employees = employeesQuery.getResultList();
        return employees;
    }

    public void addHobby(int employeeId, String hobbyName) {
        Employee employee = findById(employeeId);

        // create hobby
        Hobbies newHobby = new Hobbies();
        newHobby.setName(hobbyName);
        newHobby.setEmployee(employee);

        // setting the relationship
        List<Hobbies> hobbies = employee.getHobbies();
        hobbies.add(newHobby);

        newHobby.setEmployee(employee);

        // save hobby to DB, hobbies is the owing side of the relationship
        entityManager.persist(newHobby);
    }

    // !! flush writes the changes to the database before the transaction is completed

    // many to many persist
    public void addNewDepartmentTo(int employeeId, String DepartmentId) {
//        Employee employee = findById(employeeId);
//        Department department = departmentRepository.findById(DepartmentId);

        // this performs an inner join

        // Hibernate:
        // select department0_.emp_no as emp_no2_1_0_, department0_.dept_no as dept_no1_1_0_, department1_.dept_no as dept_no1_0_1_, department1_.dept_name as dept_nam2_0_1_
        // from dept_emp department0_ inner join departments department1_
        // on department0_.dept_no=department1_.dept_no
        // where department0_.emp_no=?
//        List<Department> departments = employee.getDepartments();
//        departments.add(department);
//
//        List<Employee> employees = department.getEmployees();
//        employees.add(employee);
//
//        entityManager.persist(employee);
//
//        System.out.println();
    }

    public void addNewEmployerAndDepartment() {
        Employee newEmployee = new Employee();
        newEmployee.setFirstName("Nikos");
        newEmployee.setLastName("Oliveira");
        newEmployee.setBirthDate(new Date());
        newEmployee.setGender("M");
        newEmployee.setHireDate(new Date());

        Department department = new Department();
        department.setDepartmentId("D77");
        department.setDepartmentName("Super RnD");

        entityManager.persist(department);
        save(newEmployee);


        // add from both sides the entities
        newEmployee.getDepartments().add(department);
        department.getEmployees().add(newEmployee);

        // persist the owing side!
        insert(newEmployee);
    }

}
