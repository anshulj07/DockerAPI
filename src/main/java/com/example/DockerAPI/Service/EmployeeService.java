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

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public void createEmployee(Employee employee) {
        if(employee.getId() <= 0 || employee.getSalary() <= 0 || 
        (employee.getName().isEmpty() || employee.getName() == null) ||
        employee.getDepartment().isEmpty() || employee.getDepartment() == null){
            throw new CustomException("Details are missing", HttpStatus.BAD_REQUEST);
        }
        employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployee() {
        List<Employee> eList = employeeRepository.findAll();
        if(eList.size() == 0){
            throw new CustomException("No Employee Found", HttpStatus.NO_CONTENT);
        }
        return eList;
    }

    public void deleteEmployee(int id) {
        if(id <= 0) throw new CustomException("Wrong id", HttpStatus.BAD_REQUEST);
        employeeRepository.deleteById(id);
    }

    public Employee getEmployeeByName(String name) {
        return employeeRepository.getByName(name).orElseThrow(() -> new CustomException("Wrong name", HttpStatus.BAD_REQUEST));
    }
}


