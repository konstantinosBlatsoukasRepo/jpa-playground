package com.example.demo;

import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Hobbies;
import com.example.demo.entity.Passport;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.HobbiesRepository;
import com.example.demo.repository.PassportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private PassportRepository passportRepository;

	@Autowired
	private HobbiesRepository hobbiesRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println();
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		// basic CRUD ops with JPA
//		find();
//		update();
//		create();
//		delete();

		// set, detach and refresh
//		setterUpdate();
//		detach();
//		refresh();
//		List<Employee> allEmployeesJpql = employeeRepository.getAllEmployeesJpql();
//		System.out.println();

		// one to one relation: empoyees and passport is an one-to-one
        // oneToOneRelationExample();
		// eager and lazy fetch
		// lazy initialization exception! example
//		Passport passById = passportRepository.findById(4);
//		Employee employeeLazy = passById.getEmployee();
//		System.out.println();

		// Transaction example
		// 2 retrievals
		// 2 updates

		// owing side, is the one that holds the key
		// Unidirectional OneToOne
		// Bidirectional OneToOne, @OneToOne(fetch=FetchType.LAZY, mappedBy="passport")
		// mappedBy, is added in the not owning relation

		// @OneToMany @ManyToOne hobbies - employee addHobby(int employeeId, String hobbyName)
		// @ManyToOne, the fetching is eager, the default
		// while @OneToMany is lazy
		// **ToMany is Lazy (produces an extra select)
		// **ToOne is Eager (left join)
		// @JoinColumn important! for not creating the extra table

		// @ManyToMany, uf you don't specify an owing side two tables will be created!
		// If there is already a table specify the @JoinTable or you want to customize it
		// @JoinTable goes at the owing side of the relationship
		// specify owing side by using the mappedBy!

//		employeeRepository.addNewDepartmentTo(10018, "d004");
		employeeRepository.addNewEmployerAndDepartment();

		System.out.println();

	}

	private void find() {
		Employee employee = employeeRepository.findById(10001);
	}

	private void update() {
		Employee employee = employeeRepository.findById(10001);
		employee.setFirstName("Kostas");

		// update
		employeeRepository.update(employee);
	}

	private void create() {
		Employee newEmployee = new Employee();
		newEmployee.setFirstName("Nikos");
		newEmployee.setLastName("Oliveira");
		newEmployee.setBirthDate(new Date());
		newEmployee.setGender("M");
		newEmployee.setHireDate(new Date());
		employeeRepository.insert(newEmployee);
	}

	private void delete() {
		employeeRepository.deleteById(1);
	}

	private void setterUpdate() {
		employeeRepository.playWithEntityManger(10002);
	}

	private void detach() {
		employeeRepository.playWithEntityMangerDetach(10002);
	}

	private void refresh() {
		employeeRepository.playWithEntityManagerRefresh(10002);
	}

	private void oneToOneRelationExample() {
		passportRepository.savePassportWithEmployee();
		System.out.println();
		Passport passById = passportRepository.findById(4);
		System.out.println();
	}

}