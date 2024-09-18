package com.tax.service;

import com.tax.model.Employee;
import com.tax.model.ResponseEmployee;

public interface ITaxCalculatorService {
	
	public String sbbmitEmployee(Employee employee);
	
	public ResponseEmployee getEmployeeById(String id);
	
	public boolean checkEmployee(String id);

}
