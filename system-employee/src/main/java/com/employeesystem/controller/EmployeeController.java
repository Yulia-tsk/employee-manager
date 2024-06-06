package com.employeesystem.controller;

import com.employeesystem.model.Employee;
import com.employeesystem.model.ResponseStatus;
import com.employeesystem.service.EmployeeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
@GetMapping("/employees")

    public List<Employee> getEmployees(){
        return employeeService.getEmployees();
    }


    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeByID(@PathVariable Long id) throws Throwable {
          Employee emp = employeeService.getEmployeeByID(id);

        return  ResponseEntity.ok(emp);

    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<ResponseStatus> deleteEmployee(@PathVariable Long id){
        ResponseStatus res = employeeService.delete(id);
        return ResponseEntity.ok(res); }
@PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok(employee);
    }

}
