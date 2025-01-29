package com.mindfire.ems.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mindfire.ems.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findBySalaryGreaterThan(double amount);

    List<Employee> findByDateOfJoiningAfter(LocalDate localDate);

    @Query(nativeQuery = true,
     value = "SELECT * FROM employee WHERE salary >= (SELECT  DISTINCT salary FROM employee ORDER BY salary DESC LIMIT 1 OFFSET 2)")
    List<Employee> earningMoreThanThirdHighest();
}
