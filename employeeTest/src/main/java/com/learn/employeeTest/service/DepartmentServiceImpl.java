package com.learn.employeeTest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.employeeTest.ExceptionHandling.ResourceNotFoundException;
import com.learn.employeeTest.entity.Department;
import com.learn.employeeTest.repository.DepartmentRepo;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentRepo departmentRepo;
	
	@Override
	public Department addDepartment(Department department) {
		
		return departmentRepo.save(department);
	}

	@Override
	public List<Department> getAllDepartments() {
		
		return departmentRepo.findAll();
	}

	@Override
	public Department getDepartmentById(int id) {
		
		return departmentRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(" Department Not found with id : "+id));
	}

	@Override
	public Department updateDepartmentById(int id, Department updatedDepartment) {
		
		Department department = getDepartmentById(id);
		
		department.setDeptName(updatedDepartment.getDeptName());
		
		return departmentRepo.save(department);
	}
	
	@Override
	public void deleteDepartment(int id) {
	    Department department = getDepartmentById(id); 
	    departmentRepo.deleteById(id);
	}


}
