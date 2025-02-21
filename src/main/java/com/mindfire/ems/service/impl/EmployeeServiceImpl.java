package com.mindfire.ems.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mindfire.ems.Exception.ResourceNotFoundException;
import com.mindfire.ems.constants.MessageConstants;
import com.mindfire.ems.dto.EmployeeRequestDto;
import com.mindfire.ems.dto.EmployeeResponseDto;
import com.mindfire.ems.dto.PagingResult;
import com.mindfire.ems.model.Department;
import com.mindfire.ems.model.Employee;
import com.mindfire.ems.repository.DepartmentRepository;
import com.mindfire.ems.repository.EmployeeRepository;
import com.mindfire.ems.service.EmployeeService;
import com.mindfire.ems.utility.EmployeeResponseMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public EmployeeResponseDto addEmployee(EmployeeRequestDto dto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(dto, employee);
        List<Department> departments = dto.deptIds().stream().map(id -> departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(MessageConstants.DEPARTMENT_NOT_FOUND))).toList();

        employee.setDepartments(departments);
        EmployeeResponseDto response = EmployeeResponseMapper
                .convertEmployeeResponseDto(employeeRepository.save(employee));

        return response;
    }

    @Override
    public EmployeeResponseDto updateEmployeeSalary(int id, double updatedSalary) {
        if (updatedSalary <= 0) {
            throw new RuntimeException(MessageConstants.VALUE_CAN_NOT_NEGATIVE_OR_ZERO);
        }

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.EMPLOYEE_NOT_FOUND));

        employee.setSalary(updatedSalary);

        EmployeeResponseDto response = EmployeeResponseMapper
                .convertEmployeeResponseDto(employeeRepository.save(employee));

        return response;
    }

    @Override
    public boolean deleteEmployee(int id) {
        if (employeeRepository.findById(id).isEmpty()) {
            return false;
        }

        employeeRepository.deleteById(id);

        return true;
    }

    @Override
    public List<EmployeeResponseDto> getEmployees() {
        return employeeRepository.findAll().stream().map(EmployeeResponseMapper::convertEmployeeResponseDto).toList();
    }

    @Override
    public EmployeeResponseDto getEmployee(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.EMPLOYEE_NOT_FOUND));

        return EmployeeResponseMapper.convertEmployeeResponseDto(employee);
    }

    @Override
    public List<EmployeeResponseDto> getEmployeeWithSalaryGreaterThan(double amount) {
        List<Employee> employees = employeeRepository.findBySalaryGreaterThan(amount);

        return employees.stream().map(EmployeeResponseMapper::convertEmployeeResponseDto).toList();
    }

    @Override
    public List<EmployeeResponseDto> getAllEmployeeJoinedInlastSixMonth() {
        LocalDate today = LocalDate.now();

        List<Employee> employees = employeeRepository.findByDateOfJoiningAfter(today.minusMonths(6));

        return employees.stream().map(EmployeeResponseMapper::convertEmployeeResponseDto).toList();
    }

    @Override
    public List<EmployeeResponseDto> earningMorethanThirdHighestSalary() {
        List<Employee> employees = employeeRepository.earningMoreThanThirdHighest();

        return employees.stream().map(EmployeeResponseMapper::convertEmployeeResponseDto).toList();
    }

    @Override
    public void bulkSalaryUpdate(double percentage) {
        if (percentage <= 0) {
            throw new RuntimeException(MessageConstants.VALUE_CAN_NOT_NEGATIVE_OR_ZERO);
        }

        employeeRepository.bulkSalaryUpdate(percentage);
    }

    @Override
    public PagingResult<EmployeeResponseDto> getEmployeeInBatchSortBySalaryInDesc(int pageNumber) {
        if (pageNumber < 0) {
            throw new RuntimeException(MessageConstants.VALUE_CAN_NOT_NEGATIVE_OR_ZERO);
        }

        int pageSize = 5;

        Sort sort = Sort.by("salary").descending();

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);

        Page<Employee> pageEmployee = employeeRepository.findAll(pageable);

        List<EmployeeResponseDto> employees = pageEmployee.getContent().stream()
                .map(EmployeeResponseMapper::convertEmployeeResponseDto).toList();

        PagingResult<EmployeeResponseDto> response = new PagingResult<>(employees, pageEmployee.getTotalPages(),
                pageEmployee.getTotalElements(), pageEmployee.getSize(), pageEmployee.getNumber());

        return response;
    }

    @Override
    public EmployeeResponseDto updateEmployee(int empId, EmployeeRequestDto dto) {
        System.out.println("Employee id is" + empId);
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.EMPLOYEE_NOT_FOUND));

        System.out.println(dto);
        System.out.println(employee);
        employee.setName(dto.name());
        employee.setEmail(dto.email());
        employee.setSalary(dto.salary());
        employee.setDateOfJoining(dto.dateOfJoining());

        List<Department> departments = dto.deptIds().stream().map(id -> departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(MessageConstants.DEPARTMENT_NOT_FOUND)))
                .collect(Collectors.toList());
        employee.setDepartments(null);
        employee.setDepartments(departments);
        try {
            employee = employeeRepository.save(employee);
            System.out.println(employee);
        } catch (Exception e) {
            throw new RuntimeException("Error saving employee: " + e);
        }
        return EmployeeResponseMapper.convertEmployeeResponseDto(employee);
    }

    @Override
    public PagingResult<EmployeeResponseDto> getEmployeeInBatch(int pageNumber) {
        if (pageNumber < 0) {
            throw new RuntimeException(MessageConstants.VALUE_CAN_NOT_NEGATIVE_OR_ZERO);
        }

        int pageSize = 5;

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);

        Page<Employee> pageEmployee = employeeRepository.findAll(pageable);

        List<EmployeeResponseDto> employees = pageEmployee.getContent().stream()
                .map(EmployeeResponseMapper::convertEmployeeResponseDto).toList();

        PagingResult<EmployeeResponseDto> response = new PagingResult<>(employees, pageEmployee.getTotalPages(),
                pageEmployee.getTotalElements(), pageEmployee.getSize(), pageEmployee.getNumber());

        return response;
    }
}
