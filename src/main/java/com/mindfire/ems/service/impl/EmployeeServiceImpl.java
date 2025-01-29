package com.mindfire.ems.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.mindfire.ems.dto.EmployeeRequestDto;
import com.mindfire.ems.dto.EmployeeResponseDto;
import com.mindfire.ems.model.Employee;
import com.mindfire.ems.repository.EmployeeRepository;
import com.mindfire.ems.service.EmployeeService;
import com.mindfire.ems.utility.EmployeeResponseMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

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
        Employee employee = employeeRepository.findById(id).orElseThrow();

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
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return EmployeeResponseMapper.convertEmployeeResponseDto(employee);
    }
}
