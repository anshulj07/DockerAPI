package com.example.DockerAPI.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DockerAPI.Model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    Optional<Employee> getByName(String name);
}
