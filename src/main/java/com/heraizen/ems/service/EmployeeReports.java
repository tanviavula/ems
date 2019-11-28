package com.heraizen.ems.service;

import java.util.List;

import com.heraizen.ems.domain.DEPT;
import com.heraizen.ems.dto.EmployeeCountDTO;

public interface EmployeeReports {
		
	public long getEmployeeCount(DEPT dept);
	public long totalEmployees();
	public List<EmployeeCountDTO> employeeCountByDept();
}
