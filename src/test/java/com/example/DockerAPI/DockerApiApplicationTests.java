package com.example.DockerAPI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.example.DockerAPI.Exception.CustomException;
import com.example.DockerAPI.Model.Employee;
import com.example.DockerAPI.Repository.EmployeeRepository;
import com.example.DockerAPI.Service.EmployeeService;

@SpringBootTest
class DockerApiApplicationTests {

	private EmployeeRepository employeeRepository;
	private EmployeeService employeeService;

	@Test
	void contextLoads() {
	}

	@BeforeEach
	void Setup(){
		employeeRepository = mock(EmployeeRepository.class);
		employeeService = new EmployeeService(employeeRepository);
	}

	@Test
	void createEmployee(){
		when(employeeRepository.save(employee1)).thenReturn(employee1);
		employeeRepository.save(employee1);
		verify(employeeRepository, times(1)).save(employee1);
	}

	@Test
	void createEmployee_INVALIDDATA(){
		Employee invalid = new Employee(1, "", "", 70000.0);
		CustomException customException = assertThrows(CustomException.class, () -> employeeService.createEmployee(invalid));
		assertEquals(HttpStatus.BAD_REQUEST, customException.getStatus());
		
	}

	Employee employee1 = new Employee(1, "Anshul", "Tech", 80000.0);
	Employee employee2 = new Employee(2, "Jain", "IT", 90000.0);

	@Test
	void getAllEmployee(){
		List<Employee> lEmployees = Arrays.asList(employee1, employee2);
		when(employeeRepository.findAll()).thenReturn(lEmployees);

		List<Employee> rEmployees = employeeRepository.findAll();

		assertEquals(lEmployees, rEmployees);
		assertEquals("Anshul", rEmployees.get(0).getName());
		assertEquals(1, rEmployees.get(0).getId());
		assertEquals("Tech", rEmployees.get(0).getDepartment());
		assertEquals(80000.0, rEmployees.get(0).getSalary());
		verify(employeeRepository, times(1)).findAll();
	}

	@Test 
	void getAllEmployee_WhenListIsEmpty(){
		when(employeeRepository.findAll()).thenReturn(Collections.emptyList());

		CustomException customException = assertThrows(CustomException.class, () -> employeeService.getAllEmployee());
		
		assertEquals("No Employee Found", customException.getMessage());
		assertEquals(HttpStatus.NO_CONTENT, customException.getStatus());
	}

	@Test 
	void deleteEmployee(){

	}

	@Test 
	void getEmployeeByName_WHENPRESENT(){
		when(employeeRepository.getByName("Anshul")).thenReturn(Optional.of(employee1));

		assertEquals("Anshul", employee1.getName());
	}

	@Test 
	void getEmployeeByName_WHENNOTPRESENT(){
		when(employeeRepository.getByName("Anshul")).thenReturn(Optional.empty());

		CustomException customException = assertThrows(CustomException.class, () -> employeeService.getEmployeeByName("Test"));

		assertEquals("Wrong name", customException.getMessage());
	}
}
