package com.heraizen.ems.domain;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@ToString
@Getter
@Setter
public class Employee {
		@Id
		private String id;
		private String name;
		private String email;
		private String mobile;
		private DEPT dept;
		private double salary;
		
		
	
		
}
