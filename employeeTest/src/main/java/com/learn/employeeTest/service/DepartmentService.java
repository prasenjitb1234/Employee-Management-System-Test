package com.learn.employeeTest.service;

import java.util.List;

import com.learn.employeeTest.entity.Department;

public interface DepartmentService {
	
	Department addDepartment(Department department);
	
	List<Department> getAllDepartments();
	
	Department getDepartmentById(int id);
	
	Department updateDepartmentById(int id, Department department);
	
	void deleteDepartment(int id);

	
}
