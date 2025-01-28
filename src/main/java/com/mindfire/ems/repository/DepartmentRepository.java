package com.mindfire.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindfire.ems.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
