package com.mindfire.ems.repository.impl;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mindfire.ems.dto.EmployeeResponseDto;
import com.mindfire.ems.model.Employee;
import com.mindfire.ems.repository.CustomRepository;
import com.mindfire.ems.utility.EmployeeResponseMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CustomRepositoryImpl implements CustomRepository {

    private final EntityManager entityManager;

    @Override
    @Transactional
    public void bulkSalaryUpdate() {
        TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);

        List<Employee> employees = query.getResultList();

        List<Employee> newList = employees.stream().map(emp -> {
            emp.setSalary(Math.round(emp.getSalary() + emp.getSalary() * 0.1));
            return emp;
        }).toList();

        newList.forEach(emp -> entityManager.merge(emp));
    }

}
