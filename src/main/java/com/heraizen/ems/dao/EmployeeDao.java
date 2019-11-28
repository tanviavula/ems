package com.heraizen.ems.dao;

import java.util.List;
import java.util.Optional;

import com.heraizen.ems.domain.Employee;

public interface EmployeeDao {
	public Employee addEmployee(Employee employee);

	public List<Employee> employees();

	public List<Employee> addEmployees(List<Employee> employees);

	public List<Employee> search(String str);

	public Optional<Employee> searchById(String id);

	public Employee updateEmployee(Employee employee);

	public boolean deleteEmployee(String id);
}
