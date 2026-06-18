package com.day.department_store.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Department {
    private Long id;
    private String name;
    private List<Employee> employees;
}
