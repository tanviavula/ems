package com.heraizen.ems.service;

import java.util.List;
import java.util.Optional;

import com.heraizen.ems.domain.DEPT;
import com.heraizen.ems.domain.Employee;

public interface EmployeeService {

	public Employee addEmployee(Employee employee);
	public List<Employee> addEmployees(List<Employee> employees);
	
	public List<Employee> employees();

	public List<Employee> search(String str);

	public Optional<Employee> searchById(String id);

	public Employee updateEmployee(Employee employee);

	public boolean deleteEmployee(String id);

}
