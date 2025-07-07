package com.learn.employeeTest.service;

import java.util.List;

import com.learn.employeeTest.entity.Employee;

public interface EmployeeService {

	List<Employee> getAllEmployees();
	
	Employee getEmployeeById(int id);
	
	Employee createEmployee(Employee employee);
	
	Employee updateEmployee(int id, Employee employee);
	
	void deleteEmployee(int id);
	
	//
	
	List<Employee> getEmployeesByDepartmentId(int deptId);
	
	List<Employee> getEmployeesByCity(String city);
	
}
