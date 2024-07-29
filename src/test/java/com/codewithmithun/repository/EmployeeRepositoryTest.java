package com.codewithmithun.repository;

import com.codewithmithun.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest

class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testCreateAndFindEmployee() {
        Employee employee = new Employee(null, "John Doe", "Engineering", 50000.0);
        Employee savedEmployee = employeeRepository.save(employee);

        Optional<Employee> retrievedEmployee = employeeRepository.findById(savedEmployee.getId());
        assertTrue(retrievedEmployee.isPresent());
        assertEquals(savedEmployee, retrievedEmployee.get());
    }

    @Test
    public void testFindAllEmployees() {
        Employee employee1 = new Employee(null, "John Doe", "Engineering", 50000.0);
        Employee employee2 = new Employee(null, "Jane Smith", "Marketing", 60000.0);
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        List<Employee> employees = employeeRepository.findAll();
        assertEquals(2, employees.size());
    }

}