package com.example.DockerAPI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DockerAPI.Model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    Employee getByName(String name);
}
