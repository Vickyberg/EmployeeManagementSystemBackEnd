package com.EmployeeManagementSystem.volacode.repositories;

import com.EmployeeManagementSystem.volacode.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
