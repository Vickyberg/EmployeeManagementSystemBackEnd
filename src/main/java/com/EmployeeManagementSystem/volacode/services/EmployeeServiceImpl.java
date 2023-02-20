package com.EmployeeManagementSystem.volacode.services;

import com.EmployeeManagementSystem.volacode.dtos.requests.AddEmployeeRequest;
import com.EmployeeManagementSystem.volacode.dtos.response.AddEmployeeResponse;
import com.EmployeeManagementSystem.volacode.exceptions.EmployeeManagerException;
import com.EmployeeManagementSystem.volacode.exceptions.EmployeeNotFoundException;
import com.EmployeeManagementSystem.volacode.models.Employee;
import com.EmployeeManagementSystem.volacode.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements  EmployeeService{


    private final ModelMapper modelMapper = new ModelMapper();

    private final EmployeeRepository employeeRepository;
    @Override
    public AddEmployeeResponse add(AddEmployeeRequest request) {
        Optional<Employee> foundEmployee = employeeRepository.findByEmail(request.getEmail());
        if(foundEmployee.isPresent()) throw new EmployeeManagerException(
                String.format("Email %s has already been used", request.getEmail()));

        Employee employee = modelMapper.map(request,Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);

        return addEmployeeBuilder(savedEmployee);
    }

    private AddEmployeeResponse addEmployeeBuilder( Employee employee) {
        return AddEmployeeResponse.builder()
                .id(employee.getId())
                .message("Employee saved successfully").build();
    }

    @Override
    public void update( Long id,AddEmployeeRequest request) {

    Optional<Employee> foundEmployee =employeeRepository.findById(id);

    if(foundEmployee.isPresent()){

        Employee employeeToUpdate =foundEmployee.get();
        updateEmployee ( request,employeeToUpdate);
employeeRepository.save(employeeToUpdate);
    }

    }

    private void updateEmployee(AddEmployeeRequest request, Employee employeeToUpdate) {

        employeeToUpdate.setPhoneNumber(request.getPhoneNumber());
        employeeToUpdate.setGender(request.getGender());
    }


    @Override
    public List<Employee> getAllEmployee() {

        return  employeeRepository.findAll();
    }



    @Override
    public void delete(Long id) {

    }


}
