package com.mindfire.ems.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mindfire.ems.Exception.ResourceNotFoundException;
import com.mindfire.ems.dto.EmployeeRequestDto;
import com.mindfire.ems.dto.EmployeeResponseDto;
import com.mindfire.ems.model.Employee;
import com.mindfire.ems.repository.CustomRepository;
import com.mindfire.ems.repository.EmployeeRepository;
import com.mindfire.ems.service.EmployeeService;
import com.mindfire.ems.utility.EmployeeResponseMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final CustomRepository customRepository;

    @Override
    public EmployeeResponseDto addEmployee(EmployeeRequestDto dto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(dto, employee);

        EmployeeResponseDto response = EmployeeResponseMapper
                .convertEmployeeResponseDto(employeeRepository.save(employee));

        return response;
    }

    @Override
    public EmployeeResponseDto updateEmployeeSalary(int id, double updatedSalary) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exists"));

        employee.setSalary(updatedSalary);

        EmployeeResponseDto response = EmployeeResponseMapper
                .convertEmployeeResponseDto(employeeRepository.save(employee));

        return response;
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeResponseDto> getEmployees() {
        return employeeRepository.findAll().stream().map(EmployeeResponseMapper::convertEmployeeResponseDto).toList();
    }

    @Override
    public EmployeeResponseDto getEmployee(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exists"));

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
        customRepository.bulkSalaryUpdate(percentage);
    }

    @Override
    public List<EmployeeResponseDto> getEmployeeInBatchSortBySalaryInDesc(int pageNumber) {
        int pageSize = 5;

        Sort sort = Sort.by("salary").descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        List<Employee> employees = employeeRepository.findAll(pageable).getContent();

        return employees.stream().map(EmployeeResponseMapper::convertEmployeeResponseDto).toList();
    }
}
