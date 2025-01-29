package com.mindfire.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mindfire.ems.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    @Query("select d.name, count(e) from Department d left join d.employees e group by d.name")
    List<Object[]> countEmployeePerDepartment();
}
