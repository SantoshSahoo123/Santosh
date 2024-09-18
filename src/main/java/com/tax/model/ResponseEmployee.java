package com.tax.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEmployee {
	
	private String employeeId;
	private String firstName;
	private String lastName;
	private Double yearlySalary;
	private Double taxAmount;
	private Double cessAmount;

}
