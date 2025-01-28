package com.mindfire.ems.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.mindfire.ems.dto.DepartmentRequestDto;
import com.mindfire.ems.model.Department;
import com.mindfire.ems.repository.DepartmentRepository;
import com.mindfire.ems.service.DepartmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public Department addDepartment(DepartmentRequestDto dto) {
        Department department = new Department();
        BeanUtils.copyProperties(dto, department);

        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(int id, DepartmentRequestDto dto) {
        Department oldDepartment = getDepartment(id);
        oldDepartment.setLocation(dto.location());
        oldDepartment.setName(dto.name());

        return departmentRepository.save(oldDepartment);
    }

    @Override
    public void deleteDepartment(int id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartment(int id) {
        return departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("department not found"));
    }

}
