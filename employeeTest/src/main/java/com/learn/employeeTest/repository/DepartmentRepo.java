package com.learn.employeeTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.employeeTest.entity.Department;

public interface DepartmentRepo extends JpaRepository<Department, Integer>{

}
