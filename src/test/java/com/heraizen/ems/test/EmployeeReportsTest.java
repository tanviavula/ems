package com.heraizen.ems.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.heraizen.ems.domain.DEPT;
import com.heraizen.ems.dto.EmployeeCountDTO;
import com.heraizen.ems.service.EmployeeReports;
@SpringBootTest
public class EmployeeReportsTest {
	@Autowired
	private EmployeeReports empReports;
	@Autowired
	private SeedDataManager seedDataManager;

	
	@AfterEach
	void clear() {
		seedDataManager.deleteAll();
		
	}
	@BeforeEach
	void init() {
		seedDataManager.deleteAll();
		seedDataManager.saveAllEmployees();
	}
	
	@Test
	void employeeCountByDept() {
		List<EmployeeCountDTO> list = empReports.employeeCountByDept();
		list.forEach(System.out::println);
		assertThat(list).size().isGreaterThan(0);
	}
	@Test
	void totalEmployeeCount() {
		long count = empReports.totalEmployees();
		System.out.println("Employee count :"+count);
		assertThat(count).isEqualTo(3);
	}
	
	@Test
	void totalEmployeeCountByDept() {
		long count = empReports.getEmployeeCount(DEPT.DEV);
		System.out.println("Employee count by dept :"+count);
		assertThat(count).isEqualTo(2);
	}
}
