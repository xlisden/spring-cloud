package com.day.department_store.controller;

import com.day.department_store.client.EmployeeClient;
import com.day.department_store.model.Department;
import com.day.department_store.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/departments")
@AllArgsConstructor
@Slf4j
public class DepartmentController {

    private final DepartmentRepository repository;
    private final EmployeeClient employeeClient;

    @GetMapping
    public ResponseEntity<List<Department>> getAll() {
        List<Department> departments = repository.findAll();
        departments.forEach(department -> department.setEmployees(employeeClient.findAllByDepartmentId(department.getId())));
        return ResponseEntity.ok().body(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@PathVariable Long id) {
        Optional<Department> optionalDepartment = repository.findById(id);
        if (optionalDepartment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Department department = optionalDepartment.get();
        department.setEmployees(employeeClient.findAllByDepartmentId(id));
        return ResponseEntity.ok().body(department);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Department department) {
        repository.create(department);
        return ResponseEntity.ok().build();
    }
}
