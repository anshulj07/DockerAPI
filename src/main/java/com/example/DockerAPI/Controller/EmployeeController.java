package com.example.DockerAPI.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DockerAPI.Model.Employee;
import com.example.DockerAPI.Service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    
    private EmployeeService employeeService;

    EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping("/createEmployee")
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee){
        System.out.println(employee.getId()+" "+employee.getName()+" "+employee.getDepartment()+" "+employee.getSalary());
        employeeService.createEmployee(employee);
        return new ResponseEntity<String>("Employee record created", HttpStatus.CREATED);
    }

    @GetMapping("/getAllEmployee")
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @GetMapping("/getEmployeeByName")
    public Employee getEmployeeByName(@RequestParam String name){
        return employeeService.getEmployeeByName(name);
    }

    @DeleteMapping("/deleteEmployee")
    public ResponseEntity<String> deleteEmployee(@RequestParam int id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<String>("Employee record deleted", HttpStatus.OK);
    } 
}
