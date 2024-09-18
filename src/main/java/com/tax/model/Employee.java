package com.tax.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	
	private String employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private List<String> phoneNumbers;
	private String doj;
	private Double salary;

}
