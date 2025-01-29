package com.mindfire.ems.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.mindfire.ems.dto.DepartmentRequestDto;
import com.mindfire.ems.dto.DepartmentResponseDto;
import com.mindfire.ems.model.Department;
import com.mindfire.ems.repository.DepartmentRepository;
import com.mindfire.ems.service.DepartmentService;
import com.mindfire.ems.utility.DepartmentResponseMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentResponseDto addDepartment(DepartmentRequestDto dto) {
        Department department = new Department();
        BeanUtils.copyProperties(dto, department);

        DepartmentResponseDto response = DepartmentResponseMapper.convertDepartmentResponseDto(departmentRepository.save(department));

        return response;
    }

    @Override
    public DepartmentResponseDto updateDepartment(int id, DepartmentRequestDto dto) {
        Department oldDepartment = departmentRepository.findById(id).orElseThrow();
        
        oldDepartment.setLocation(dto.location());
        oldDepartment.setName(dto.name());

        DepartmentResponseDto response = DepartmentResponseMapper.convertDepartmentResponseDto(departmentRepository.save(oldDepartment));
        return response;
    }

    @Override
    public void deleteDepartment(int id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public List<DepartmentResponseDto> getDepartments() {
        return departmentRepository.findAll().stream().map(DepartmentResponseMapper::convertDepartmentResponseDto).toList();
    }

    @Override
    public DepartmentResponseDto getDepartment(int id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("department not found"));
        DepartmentResponseDto response = DepartmentResponseMapper.convertDepartmentResponseDto(department);

        return response;
    }

}
