package com.heraizen.ems.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.heraizen.ems.domain.Employee;
import com.heraizen.ems.service.EmployeeService;

@SpringBootTest
@DisplayName("Employee Service Test")
class EmployeeBookApplicationTests {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private SeedDataManager seedDataManager;

	@BeforeEach
	void clear() {
		seedDataManager.deleteAll();
	}

	@Test
	void addEmployeeWithValidData() {
		Employee employee = seedDataManager.getTestEmployee();
		Employee retObjct = employeeService.addEmployee(employee);
		assertAll(() -> assertThat(retObjct.getId()).isNotNull().as("Employee id can't be null"),
				() -> assertThat(employee.getName()).isEqualTo(retObjct.getName()).as("Both names must be same"));
	}

	@Test
	void addMultipleEmployees() {
		List<Employee> employees = seedDataManager.getTestEmployeeList();
		List<Employee> retEmployees = employeeService.addEmployees(employees);
		assertThat(retEmployees).size().isEqualTo(employees.size())
				.as("Insert and Retrive Employees size must be same");
		assertThat(retEmployees).allMatch(e -> !e.getId().isEmpty()).as("Inserted Employees ids must not be null or empty");
	}

	@Test
	void searchEmployeeWithExisting() {
		List<Employee> employees = seedDataManager.getTestEmployeeList();
		employeeService.addEmployees(employees);
		List<Employee> searchList_email = employeeService.search("com");
		List<Employee> searchList_name = employeeService.search("man");
		List<Employee> searchList_not_match = employeeService.search("Zee");

		assertAll(
				  () -> assertThat(searchList_email).size().isEqualTo(3).as("Result should size must be 3"),
				  () -> assertThat(searchList_name.size() == 2),
				  () -> assertTrue(searchList_not_match.size() == 0));

	}

	@Test
	void searchEmployeeById() {
		List<Employee> employees = seedDataManager.getTestEmployeeList();
		List<Employee> retList = employeeService.addEmployees(employees);
		assertTrue(retList.stream().map(e -> e.getId()).allMatch(e -> employeeService.searchById(e).isPresent()));
	}

	@Test
	void deleteEmployee() {
		List<Employee> employees = seedDataManager.getTestEmployeeList();
		List<Employee> retList = employeeService.addEmployees(employees);
		Employee employee = retList.get(0);
		boolean isDeleted = employeeService.deleteEmployee(employee.getId());
		List<Employee> afterDeleteList = employeeService.employees();
		assertAll(() -> assertTrue(isDeleted), () -> assertTrue(afterDeleteList.size() == 2));
	}

	@Test
	void updateEmployee() {
		Employee employee = seedDataManager.getTestEmployee();
		Employee savedEmployee = employeeService.addEmployee(employee);
		savedEmployee.setName("Ramesh");
		savedEmployee.setEmail("ramesh.n@heraizen.com");

		Employee updatedEmployee = employeeService.updateEmployee(savedEmployee);

		assertAll(() -> assertTrue(savedEmployee.getName().equals(updatedEmployee.getName())),
				() -> assertTrue(savedEmployee.getEmail().equals(updatedEmployee.getEmail())),
				() -> assertTrue(savedEmployee.getId().equals(updatedEmployee.getId())));
	}

}
