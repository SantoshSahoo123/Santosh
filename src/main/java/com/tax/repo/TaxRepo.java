package com.tax.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tax.model.EmployeeDAO;

public interface TaxRepo extends JpaRepository<EmployeeDAO, String>{

}
