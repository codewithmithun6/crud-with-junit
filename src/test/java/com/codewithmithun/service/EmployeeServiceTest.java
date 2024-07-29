package com.codewithmithun.service;

import com.codewithmithun.entity.Employee;
import com.codewithmithun.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    private Employee employee1;
    private Employee employee2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        employee1 = new Employee(1L, "John Doe", "Engineering", 50000.0);
        employee2 = new Employee(2L, "Jane Smith", "Marketing", 60000.0);
    }

    @Test
    public void testCreateEmployee() {
        when(employeeRepository.save(employee1)).thenReturn(employee1);
        Employee createdEmployee = employeeService.createEmployee(employee1);
        assertEquals(employee1, createdEmployee);
    }

    @Test
    public void testGetAllEmployees() {
        List<Employee> employees = Arrays.asList(employee1, employee2);
        when(employeeRepository.findAll()).thenReturn(employees);
        List<Employee> retrievedEmployees = employeeService.getAllEmployees();
        assertEquals(employees, retrievedEmployees);
    }

    @Test
    public void testGetEmployeeById() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee1));
        Optional<Employee> retrievedEmployee = employeeService.getEmployeeById(1L);
        assertTrue(retrievedEmployee.isPresent());
        assertEquals(employee1, retrievedEmployee.get());
    }

    @Test
    public void testUpdateEmployee() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee1));
        when(employeeRepository.save(employee1)).thenReturn(employee1);
        Employee updatedEmployee = employeeService.updateEmployee(1L, employee1);
        assertEquals(employee1, updatedEmployee);
    }

    @Test
    public void testDeleteEmployee() {
        employeeService.deleteEmployee(1L);
        verify(employeeRepository, times(1)).deleteById(1L);
    }

}