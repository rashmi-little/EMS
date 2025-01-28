package com.mindfire.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindfire.ems.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
