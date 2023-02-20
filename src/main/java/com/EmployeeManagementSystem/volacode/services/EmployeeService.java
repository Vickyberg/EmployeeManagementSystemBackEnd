package com.EmployeeManagementSystem.volacode.services;


import com.EmployeeManagementSystem.volacode.dtos.requests.AddEmployeeRequest;
import com.EmployeeManagementSystem.volacode.dtos.response.AddEmployeeResponse;
import com.EmployeeManagementSystem.volacode.models.Employee;

import java.util.List;


public interface EmployeeService {

    AddEmployeeResponse add(AddEmployeeRequest request);

   void update(Long id,AddEmployeeRequest request);

    List<Employee> getAllEmployee();


    void  delete(Long id);
}
