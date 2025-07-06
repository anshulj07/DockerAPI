package com.example.DockerAPI.Service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.DockerAPI.Exception.CustomException;
import com.example.DockerAPI.Model.Employee;
import com.example.DockerAPI.Repository.EmployeeRepository;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    public Employee getEmployeeByName(String name) {
        return employeeRepository.getByName(name).orElseThrow(() -> new CustomException("Wrong name", HttpStatus.NOT_FOUND));
    }
}


