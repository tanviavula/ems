package com.heraizen.ems.dao;

import java.util.List;

import com.heraizen.ems.domain.DEPT;
import com.heraizen.ems.dto.EmployeeCountDTO;

public interface EmployeeDaoReports {
	public long getEmployeeCount(DEPT dept);

	public long totalEmployees();

	public List<EmployeeCountDTO> employeeCountByDept();
}
