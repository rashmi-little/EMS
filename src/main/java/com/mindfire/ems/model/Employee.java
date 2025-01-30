package com.mindfire.ems.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique = true)
    private String email;

    private double salary;

    private LocalDate dateOfJoining;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinTable(name = "employee_department", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "department_id"))
    private List<Department> departments;

    public void addDepartment(Department department) {
        if(departments == null) {
            departments = new ArrayList<Department>();
        }

        departments.add(department);
    }
}
