package com.heraizen.ems.dao;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.heraizen.ems.domain.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeDaoImpl.class);

	@Autowired
	private MongoOperations mongoOps;
	
	@Autowired
	private EmployeeRepo employeeRepo;

	@Override
	public Employee addEmployee(Employee employee) {
		employee = employeeRepo.save(employee);
		LOG.info("Employee is added with id:{}" + employee.getId());
		return employee;
	}

	@Override
	public List<Employee> employees() {
		return employeeRepo.findAll();
	}

	@Override
	public List<Employee> search(String str) {
		
		Criteria regex = new Criteria().orOperator(Criteria.where("email").regex(str, "i"),Criteria.where("name").regex(str,"i"));
		LOG.info("Query :{}"+regex.getCriteriaObject());
		List<Employee> searchList = mongoOps.find(new Query().addCriteria(regex), Employee.class);
		
		return searchList;
	}

	@Override
	public Optional<Employee> searchById(String id) {
		return employeeRepo.findById(id);
		
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}

	@Override
	public boolean deleteEmployee(String id) {
		Optional<Employee> employee = employeeRepo.findById(id);
		if (employee.isPresent()) {
			employeeRepo.delete(employee.get());
			return true;
		}
		LOG.info("Employee is not found with id: {}", id);
		return false;
	}

	@Override
	public List<Employee> addEmployees(List<Employee> employees) {
		employees = employeeRepo.saveAll(employees);
		return employees;
	}

}
