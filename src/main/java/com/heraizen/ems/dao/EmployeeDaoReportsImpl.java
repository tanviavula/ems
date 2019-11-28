package com.heraizen.ems.dao;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.heraizen.ems.domain.DEPT;
import com.heraizen.ems.domain.Employee;
import com.heraizen.ems.dto.EmployeeCountDTO;

@Repository
public class EmployeeDaoReportsImpl implements EmployeeDaoReports {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeDaoReportsImpl.class);

	private MongoOperations mongoOperations;

	@Autowired
	public EmployeeDaoReportsImpl(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}

	@Override
	public long getEmployeeCount(DEPT dept) {
		Query query = new Query();
		query.addCriteria(Criteria.where("dept").is(dept));
		long count = this.mongoOperations.count(query, Employee.class);
		return count;
	}

	@Override
	public long totalEmployees() {
		Query query = new Query();
		return this.mongoOperations.count(query, Employee.class);
	}

	@Override
	public List<EmployeeCountDTO> employeeCountByDept() {
		Aggregation agg = newAggregation(
				group("dept").count().as("count"), project().and("_id").as("dept").and("count").as("count")
		);

		List<EmployeeCountDTO> dbObjects = mongoOperations.aggregate(agg, "employee", EmployeeCountDTO.class)
				.getMappedResults();
		LOG.info("Employee count by deptno : " + agg);
		return dbObjects;
	}

}
