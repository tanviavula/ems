package com.heraizen.ems.dto;

import com.heraizen.ems.domain.DEPT;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class EmployeeCountDTO {
	
		private DEPT dept;
		private int count;
		
}
