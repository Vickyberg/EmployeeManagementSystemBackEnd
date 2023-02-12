package com.EmployeeManagementSystem.volacode.repositories;

import com.EmployeeManagementSystem.volacode.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
