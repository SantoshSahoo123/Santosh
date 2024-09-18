package com.tax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tax.model.Employee;
import com.tax.model.ResponseEmployee;
import com.tax.service.ITaxCalculatorService;

@RestController
@RequestMapping(value = "/api")
public class taxController {
	
	@Autowired
	private ITaxCalculatorService service;
	
	@PostMapping("/employees")
	public String storeEmployee(@Validated @RequestBody Employee employee) {
		try {
		if(service.checkEmployee(employee.getEmployeeId())) {
		System.out.println(employee.toString());
		return service.sbbmitEmployee(employee);
		} else {
			return "duplicate id found";
		}
		} catch (Exception e) {
			e.printStackTrace();
			return "error in handing request \n================================\n" + e.getMessage();
		}
	}
	
	@GetMapping("/employees/{employeeId}/tax-deductions")
	public ResponseEntity<ResponseEmployee> getEmployee(@PathVariable String employeeId){
		ResponseEmployee employeeById = service.getEmployeeById(employeeId);
		return new ResponseEntity(employeeById, HttpStatus.CREATED);
	}

}
