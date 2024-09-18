package com.tax.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class EmployeeDAO {
	
	@Id
	@Column(name = "EMPLOYEEID")
	private String employeeId;
	
	@Column(name = "FIRSTNAME")
	private String firstName;
	
	@Column(name = "LASTNAME")
	private String lastName;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "PHONENUMBERS")
	private String phoneNumbers;
	
	@Column(name  = "DOJ")
	private String doj;
	
	@Column(name = "SALARY")
	private Double salary;

}
