package com.heraizen.ems.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.heraizen.ems.domain.Employee;

public interface EmployeeRepo extends MongoRepository<Employee,String>{

}
