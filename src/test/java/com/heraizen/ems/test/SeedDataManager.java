package com.heraizen.ems.test;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.heraizen.ems.dao.EmployeeRepo;
import com.heraizen.ems.domain.DEPT;
import com.heraizen.ems.domain.Employee;
@Component
public class SeedDataManager {
	@Autowired
	private EmployeeRepo employeeRepo;

	public Employee getTestEmployee() {
		Employee employee = new Employee();
		employee.setName("Krish");
		employee.setMobile("9036102111");
		employee.setEmail("Krish@gmail.com");
		employee.setDept(DEPT.DEV);
		employee.setSalary(45000);
		return employee;
	}

	public List<Employee> getTestEmployeeList() {
		Employee employee_2 = new Employee();
		employee_2.setName("Krish");
		employee_2.setMobile("9036102111");
		employee_2.setEmail("Krish@spaneos.com");
		employee_2.setDept(DEPT.DEV);
		employee_2.setSalary(45000);

		Employee employee_1 = new Employee();
		employee_1.setName("Manoj");
		employee_1.setMobile("9036102113");
		employee_1.setEmail("manoj@heraizen.com");
		employee_1.setDept(DEPT.QA);
		employee_1.setSalary(65000);

		Employee employee_3 = new Employee();
		employee_3.setName("Manasa");
		employee_3.setMobile("9036102114");
		employee_3.setEmail("manasa@spaneos.com");
		employee_3.setDept(DEPT.DEV);
		employee_3.setSalary(75000);
		return Arrays.asList(employee_1, employee_2, employee_3);
	}
	
	public void saveAllEmployees() {
		employeeRepo.saveAll(getTestEmployeeList());
	}
	
	public void deleteAll() {
		employeeRepo.deleteAll();
	}
	

}
