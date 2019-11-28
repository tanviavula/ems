package com.heraizen.ems.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.heraizen.ems.dao.EmployeeDao;
import com.heraizen.ems.domain.Employee;

@Service
public class EmployeServiceImpl implements EmployeeService {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeServiceImpl.class);
	private EmployeeDao employeeDao;

	@Autowired
	public EmployeServiceImpl(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	public Employee addEmployee(Employee employee) {
		Assert.notNull(employee, "Employee object can't be null");
		Assert.notNull(employee.getName(),"Employee name can't be null / empty");
		LOG.info("Employee with name {} is going add", employee.getName());
		return employeeDao.addEmployee(employee);

	}

	@Override
	public List<Employee> addEmployees(List<Employee> employees) {
		Assert.notNull(employees, "Employee object can't be null");
		Assert.isTrue(employees.size() > 0, "Employees list should have at least one employee details");
		LOG.info("Employees with size : {} are going add", employees.size());
		employees = employeeDao.addEmployees(employees);
		LOG.info("Employees with size : {} are added", employees.size());
		return employees;
	}
	

	@Override
	public List<Employee> employees() {
		List<Employee> employees = employeeDao.employees();
		LOG.info("Total employees found : {}", employees.size());
		return employees;
	}

	@Override
	public List<Employee> search(String str) {
		Assert.notNull(str, "Search string shouldn't be null");
		List<Employee> searchList = employeeDao.search(str);
		LOG.info("Total employees found for :{} is :{}", str, searchList.size());
		return searchList;
	}

	@Override
	public Optional<Employee> searchById(String id) {
		LOG.info("Searching requested for id:{}", id);
		return employeeDao.searchById(id);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		Assert.notNull(employee, "Employee shouldn't be null");
		Assert.notNull(employee.getId(), "Employee id shouldn't null");
		employee = employeeDao.updateEmployee(employee);
		LOG.info("Employee with id : {} is updated successfully", employee.getId());
		return employee;
	}

	@Override
	public boolean deleteEmployee(String id) {

		boolean isDelted = employeeDao.deleteEmployee(id);
		if (isDelted) {
			LOG.info("Employee with id :{} is deleted", id);
		} else {
			LOG.info("Employee with id :{} couldn't delete", id);
		}
		return isDelted;
	}

}
