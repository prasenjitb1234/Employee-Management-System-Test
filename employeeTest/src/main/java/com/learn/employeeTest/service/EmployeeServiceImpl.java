package com.learn.employeeTest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.employeeTest.ExceptionHandling.ResourceNotFoundException;
import com.learn.employeeTest.entity.Employee;
import com.learn.employeeTest.repository.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Override
	public List<Employee> getAllEmployees() {
		
		return employeeRepo.findAll();
	}

	@Override
	public Employee createEmployee(Employee employee) {
		
		return employeeRepo.save(employee);
	}

	@Override
	public Employee getEmployeeById(int id) {
		
		return employeeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not Found with id : "+id));
	}

	@Override
	public Employee updateEmployee(int id, Employee updatedEmployee) {
		
		Employee employee = getEmployeeById(id);
		
		employee.setFirstName(updatedEmployee.getFirstName());
		employee.setLastName(updatedEmployee.getLastName());
		employee.setGender(updatedEmployee.getGender());
		employee.setCity(updatedEmployee.getCity());
		employee.setPhoneNo(updatedEmployee.getPhoneNo());
		employee.setDepartment(updatedEmployee.getDepartment());
		
		return employeeRepo.save(employee);
	}

	@Override
	public void deleteEmployee(int id) {
		
		Employee employee = employeeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cannot delete. Employee not found with id: " + id));
		
		employeeRepo.delete(employee);
		
		
	}

	@Override
	public List<Employee> getEmployeesByDepartmentId(int deptId) {
		
		return employeeRepo.findByDepartmentDeptId(deptId);
	}
	
	@Override
	public List<Employee> getEmployeesByCity(String city) {
	    return employeeRepo.findByCityIgnoreCase(city);
	}

}
