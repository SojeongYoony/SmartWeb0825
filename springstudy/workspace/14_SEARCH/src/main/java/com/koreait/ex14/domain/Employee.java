package com.koreait.ex14.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Employee {
	
	private int employeeId, salary, managerId, departmentId;
	private String firstName, lastName, email, phoneNumber, jobId;
	private Date hireDate;
	private double commissionPct;
	
	private Department department; // Bean 설명중 Car-Engine과 같은 관계.
	
	
}
