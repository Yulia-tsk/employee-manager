package com.employeesystem.service;

import com.employeesystem.entity.EmployeeEntity;
import com.employeesystem.model.Employee;
import com.employeesystem.model.ResponseStatus;
import com.employeesystem.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, employeeEntity);
        employeeRepository.save(employeeEntity);
        return employee;
    }

    @Override
    public List<Employee> getEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();

        return employeeEntities.stream().map(employeeEntity -> new Employee(
                employeeEntity.getId(),
                employeeEntity.getFirstName(),
                employeeEntity.getLastName(),
                employeeEntity.getEmail()
        )).toList();
    }

    @Override
    public ResponseStatus delete(Long id) {
        ResponseStatus result = new ResponseStatus();
        try {
            EmployeeEntity empEnt = employeeRepository.getReferenceById(id);
            employeeRepository.delete(empEnt);
            result.setMessage("success");
        } catch (Exception e) {
            result.setMessage(e.getMessage());
        }
        return result;
    }



//    @Override
//    public Employee getEmployeeByID(Long id) throws Throwable {
//        Optional employeeOpt = employeeRepository.findById(id);
//        Employee employee = null;
//        try {
//            employee = (Employee) employeeOpt.orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));
//        } catch (Throwable e) {
//            throw new RuntimeException(e);
//        }
//
//
//
//        return employee;
//    }


    @Override
  public Employee getEmployeeByID(Long id){
        EmployeeEntity empl = employeeRepository.findById(id).orElseThrow();
        Employee employee = new Employee();
        BeanUtils.copyProperties(empl, employee);
        return employee;
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        EmployeeEntity employeeEntity =  employeeRepository.findById(id).orElseThrow();
        employeeEntity.setEmail(employee.getEmail());
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());

        employeeRepository.save(employeeEntity);
        return employee;
    }
}
