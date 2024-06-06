package com.employeesystem.service;

import com.employeesystem.model.Employee;
import com.employeesystem.model.ResponseStatus;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    List<Employee> getEmployees();
    ResponseStatus delete(Long id);

    Employee getEmployeeByID(Long id) throws Throwable;

    Employee updateEmployee(Long id, Employee employee);
}
