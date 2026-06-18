package com.day.department_store.repository;

import com.day.department_store.model.Department;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DepartmentRepository {
    private final List<Department> departments;

    public DepartmentRepository() {
        this.departments = new ArrayList<>();
    }

    public List<Department> findAll() {
        return departments;
    }

    public void create(Department department) {
        departments.add(department);
    }

    public Optional<Department> findById(Long id) {
        return departments.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }
}
