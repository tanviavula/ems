package com.heraizen.ems.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heraizen.ems.domain.Employee;
import com.heraizen.ems.service.EmployeeService;

@RestController
@RequestMapping("/emp/")

public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	@GetMapping
	public List<Employee> employees() {
		return empService.employees();
	}

	@PostMapping
	public Employee addEmployee(@RequestBody Employee employee) {
		return empService.addEmployee(employee);
	}

	@PutMapping
	public Employee updateEmployee(@RequestBody Employee employee) {
		return empService.updateEmployee(employee);
	}

	@DeleteMapping("{id}")
	public boolean deleteEmployee(@PathVariable String id) {
		return empService.deleteEmployee(id);
	}

	@GetMapping("search/{str}")
	public List<Employee> searchEmployee(@PathVariable("str") String str) {
		return empService.search(str);
	}

}
