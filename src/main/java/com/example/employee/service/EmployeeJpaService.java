/*
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 */

// Write your code here
package com.example.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.example.employee.repository.EmployeeRepository;
import com.example.employee.repository.EmployeeJpaRepository;
import com.example.employee.model.Employee;

import java.util.*;

@Service
public class EmployeeJpaService implements EmployeeRepository {
    
    @Autowired
    private EmployeeJpaRepository employeeRepository;

    @Override
    public ArrayList<Employee> getEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        ArrayList<Employee> employees = new ArrayList<>(employeeList);
        return employees;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        try {
            Employee existingEmployee = employeeRepository.findById(employeeId).get();
            return existingEmployee;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override 
    public Employee updateEmployee(int employeeId, Employee employee) {
        
        try {
            Employee newEmployee = employeeRepository.findById(employeeId).get();

            if (employee.getEmployeeName() != null) {
                newEmployee.setEmployeeName(employee.getEmployeeName());
            }

            if (employee.getEmail() != null) {
                newEmployee.setEmail(employee.getEmail());
            }

            if (employee.getDepartment() != null) {
                newEmployee.setDepartment(employee.getDepartment());
            }

            employeeRepository.save(newEmployee);
            return newEmployee;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteEmployee(int employeeId) {
        try {
            employeeRepository.deleteById(employeeId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    
}