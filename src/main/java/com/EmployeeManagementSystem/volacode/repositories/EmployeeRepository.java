package com.EmployeeManagementSystem.volacode.repositories;

import com.EmployeeManagementSystem.volacode.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Optional<Employee> findByEmail(String email);


}
