package com.learn.employeeTest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.employeeTest.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>{

	
	List<Employee> findByDepartmentDeptId(int deptId);
	
	List<Employee> findByCityIgnoreCase(String city);
	
	
}
