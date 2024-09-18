package com.tax.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tax.model.Employee;
import com.tax.model.EmployeeDAO;
import com.tax.model.ResponseEmployee;
import com.tax.repo.TaxRepo;

@Service
public class TaxCalculatorServiceImpl implements ITaxCalculatorService{
	
	@Autowired
	private TaxRepo repo;

	@Override
	public String sbbmitEmployee(Employee employee) {
		
		boolean isId, isFirstName, isLastName, isEmail, isPhones, isDOJ, isSalary;
		
		isId = validateId(employee.getEmployeeId());
		isFirstName = validateName(employee.getFirstName());
		isLastName = validateName(employee.getLastName());
		isEmail = validateEmail(employee.getEmail());
		isPhones = validatePhones(employee.getPhoneNumbers());
		isDOJ = validateDOJ(employee.getDoj());
		isSalary = validateSalary(employee.getSalary());
		
		if(isId && isFirstName && isLastName && isEmail && isPhones && isDOJ && isSalary) {
		EmployeeDAO dao = new EmployeeDAO();
		BeanUtils.copyProperties(employee, dao);
		dao.setPhoneNumbers(employee.getPhoneNumbers().toString());
		EmployeeDAO save = repo.save(dao);
		
		return (save == null) ? "employee not inserted" : "employee inserted :: " + save.getEmployeeId();
		} else {
			return getErrorDataInfo(isId, isFirstName, isLastName, isEmail, isPhones, isDOJ, isSalary);
			
		}
	}
	
	@Override
	public ResponseEmployee getEmployeeById(String id) {
		// TODO Auto-generated method stub
		ResponseEmployee response = new ResponseEmployee();
		EmployeeDAO employeeDAO = repo.findById(id).get();
		response.setEmployeeId(employeeDAO.getEmployeeId());
		response.setFirstName(employeeDAO.getFirstName());
		response.setLastName(employeeDAO.getLastName());
		response.setYearlySalary((employeeDAO.getSalary() * 12));
		response.setTaxAmount(calculateTax(employeeDAO.getSalary() * 12));
		response.setCessAmount(calculateCess(employeeDAO.getSalary() * 12));
		return response;
	}

	@Override
	public boolean checkEmployee(String id) {
		try {
			repo.findById(id).get();
			return false;
		} catch(Exception e) {
			e.printStackTrace();
			return true;
		}
	}
	
	private Double calculateCess(Double salary) {
		if(salary > 2500000) {
			return (salary - 2500000) * 0.02;
		}
		return 0d;
	}
	
	private Double calculateTax(Double salary) {
		 double tax = 0;
		 salary -= 250000;
		 if(salary >= 250000) {
			 tax += 250000 * 0.05;
			 salary -= 250000;
		 } else {
			 tax += salary * 0.05;
			 salary = 0d;
		 }
		 
		 if(salary >= 500000) {
			 tax += 500000 * 0.10;
			 salary -= 500000;
		 } else {
			 tax += salary * 0.10;
			 salary = 0d;
		 }
		 
		 if(salary > 0) {
			 tax += salary * 0.20;
		 }
		 		 
		return tax;
	}
	
	
	private boolean validateId(String id) {
		if(id.startsWith("E") && id.length()>3){
			try {
				Integer.parseInt(id.substring(1));
				return true;
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	private boolean validateName(String name) {
		if(name.contains(" ")) {
			return false;
		} else {
			List<String> numbers = Arrays.asList("1","2","3","4","5","6","7","8","9","0");
			String[] split = name.split("");
			for(String letter : split) {
				if(numbers.contains(letter)) {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean validateEmail(String email) {
		if(!email.contains("@example.com") || !(email.indexOf("@example.com") > 0) || (email.split("@example.com").length > 1)){
			return false;
		}
		
		return true;
	}
	
	private boolean validatePhones(List<String> phones) {
		for(String phone : phones) {
			try {
				Long.parseLong(phone);
				return (phone.length() != 10) ? false : true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		return false;
	}
	
	private boolean validateDOJ(String doj) {
		if(doj.length() == 10 && doj.contains("-")) {
			try {
			String[] split = doj.split("-");
			if(split[0].length() == 4 && split[1].length() == 2 && split[2].length() == 2) {
				if(Integer.parseInt(split[0]) <= 2024 && Integer.parseInt(split[1]) > 0 && Integer.parseInt(split[1]) <= 12 
						&& Integer.parseInt(split[2]) > 0 && Integer.parseInt(split[2]) <= 31) {
				return true;
				} else {
					return false;
				}
			}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		return false;
	}
	
	private boolean validateSalary(Double salary) {
		if(salary <= 0) {
			return false;
		}
		return true;
	}
	
	private String getErrorDataInfo(boolean isId,boolean isFirstName,boolean isLastName,boolean isEmail,boolean isPhones,boolean isDOJ,boolean isSalary) {
		StringBuffer sb = new StringBuffer();
		if(!isId) {
			
			sb.append("invalid employee id :: ");
		}
		if(!isFirstName) {
			
			sb.append("invalid firstname :: ");
		}
		if(!isLastName) {
			
			sb.append("invalid lastname :: ");
		}
		if(!isEmail) {
			
			sb.append("invalid email :: ");
		}
		if(!isPhones) {
			
			sb.append("invalid phones :: ");
		}
		if(!isDOJ) {
			
			sb.append("invalid DOJ :: ");
		}
		if(!isSalary) {
			
			sb.append("invalid Salary :: ");
		}
		return sb.toString();
	}



}









