package com.mindfire.ems.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindfire.ems.Exception.ResourceNotFoundException;
import com.mindfire.ems.constants.MessageConstants;
import com.mindfire.ems.dto.DepartmentResponseDto;
import com.mindfire.ems.dto.EmployeeResponseDto;
import com.mindfire.ems.dto.EmployeeWithDepartmentDto;
import com.mindfire.ems.model.Department;
import com.mindfire.ems.model.Employee;
import com.mindfire.ems.repository.DepartmentRepository;
import com.mindfire.ems.repository.EmployeeRepository;
import com.mindfire.ems.service.RelationShipService;
import com.mindfire.ems.utility.DepartmentResponseMapper;
import com.mindfire.ems.utility.EmployeeResponseMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RelationShipServiceImpl implements RelationShipService {

    private final EmployeeRepository employeeRepository;

    private final DepartmentRepository departmentRepository;

    @Override
    public boolean addEmployeeToDepartment(int empId, int deptId) {
        Department department = departmentRepository.findById(deptId)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.DEPARTMENT_NOT_FOUND));
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.EMPLOYEE_NOT_FOUND));

        if (department.getEmployees().contains(employee)) {
            return false;
        }
        List<Employee> currentEmployees = department.getEmployees();
        currentEmployees.add(employee);
        department.setEmployees(currentEmployees);

        departmentRepository.save(department);
        return true;
    }

    @Override
    public List<EmployeeResponseDto> getAllEmployee(int deptId) {
        Department department = departmentRepository.findById(deptId)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.DEPARTMENT_NOT_FOUND));

        return department.getEmployees().stream().map(EmployeeResponseMapper::convertEmployeeResponseDto).toList();
    }

    @Override
    public List<DepartmentResponseDto> getAllAssociatedDepartments(int empId) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.EMPLOYEE_NOT_FOUND));

        return employee.getDepartments().stream().map(DepartmentResponseMapper::convertDepartmentResponseDto).toList();
    }

    @Override
    public boolean removeEmployeeFromDepartment(int empId, int deptId) {
        Department department = departmentRepository.findById(deptId)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.DEPARTMENT_NOT_FOUND));
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.EMPLOYEE_NOT_FOUND));

        List<Employee> employees = department.getEmployees();

        if (!employees.contains(employee)) {
            return false;
        }

        employees.remove(employee);
        department.setEmployees(employees);

        departmentRepository.save(department);

        return true;
    }

    @Override
    @Transactional
    public void transfer(int empId, int fromDeptId, int toDeptId) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.EMPLOYEE_NOT_FOUND));

        Department fromDepartment = departmentRepository.findById(fromDeptId)
                .orElseThrow(() -> new ResourceNotFoundException("From " + MessageConstants.DEPARTMENT_NOT_FOUND));

        Department toDepartment = departmentRepository.findById(toDeptId)
                .orElseThrow(() -> new ResourceNotFoundException("To " + MessageConstants.DEPARTMENT_NOT_FOUND));

        List<Employee> existingListFromDepartment = fromDepartment.getEmployees();

        if (!existingListFromDepartment.contains(employee)) {
            throw new RuntimeException(MessageConstants.EMPLOYEE_NOT_PRESENT_IN_THE_DEPARTMENT);
        }

        List<Employee> existingListToDepartment = toDepartment.getEmployees();

        if (existingListToDepartment.contains(employee)) {
            throw new RuntimeException(MessageConstants.EMPLOYEE_ALREADY_PRESENT_IN_THE_DEPARTMENT);
        }

        existingListFromDepartment.remove(employee);
        existingListToDepartment.add(employee);

        fromDepartment.setEmployees(existingListFromDepartment);
        toDepartment.setEmployees(existingListToDepartment);

        departmentRepository.save(fromDepartment);
        departmentRepository.save(toDepartment);
    }

    @Override
    public EmployeeWithDepartmentDto getEmployeeWithDepartments(int empId) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.EMPLOYEE_NOT_FOUND));

        EmployeeResponseDto employeeDto = EmployeeResponseMapper.convertEmployeeResponseDto(employee);

        List<DepartmentResponseDto> departmentDtos = employee.getDepartments().stream()
                .map(DepartmentResponseMapper::convertDepartmentResponseDto).toList();

        EmployeeWithDepartmentDto response = new EmployeeWithDepartmentDto(employeeDto, departmentDtos);

        return response;
    }

    @Override
    public List<EmployeeWithDepartmentDto> getAllEmployeesWithDepartments() {
        List<Employee> employees = employeeRepository.findAll();

        List<EmployeeWithDepartmentDto> resultList = employees.stream()
                .map(emp -> getEmployeeWithDepartments(emp.getId())).collect(Collectors.toList());

        return resultList;
    }

    @Override
    public EmployeeWithDepartmentDto addEmployeeToDepartments(int empId, List<Integer> deptIds) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.EMPLOYEE_NOT_FOUND));

        List<Department> updatedDepartments = deptIds.stream().map(id -> {
            return departmentRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("From " + MessageConstants.DEPARTMENT_NOT_FOUND));
        }).collect(Collectors.toList());

        employee.setDepartments(updatedDepartments);

        employeeRepository.save(employee);

        EmployeeResponseDto employeeDto = EmployeeResponseMapper.convertEmployeeResponseDto(employee);

        List<DepartmentResponseDto> departmentDtos = employee.getDepartments().stream()
                .map(DepartmentResponseMapper::convertDepartmentResponseDto).toList();

        return new EmployeeWithDepartmentDto(employeeDto, departmentDtos);
    }

}
