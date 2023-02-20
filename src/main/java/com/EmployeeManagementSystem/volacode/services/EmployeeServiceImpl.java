package com.EmployeeManagementSystem.volacode.services;

import com.EmployeeManagementSystem.volacode.dtos.requests.AddEmployeeRequest;
import com.EmployeeManagementSystem.volacode.dtos.response.AddEmployeeResponse;
import com.EmployeeManagementSystem.volacode.dtos.response.UpdateEmployeeResponse;
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
    public UpdateEmployeeResponse update(Long id, AddEmployeeRequest request) {
        Employee employeeToUpdate = employeeRepository.findById(id).orElseThrow( ()
                -> new EmployeeNotFoundException(String.format("Employee with id %s not found ", request.getId())));
        updateRequestBuilder(request, employeeToUpdate);

        Employee updatedEmployee = employeeRepository.save(employeeToUpdate);

        return updatedBuilderResponse(updatedEmployee);
    }

    private void updateRequestBuilder(AddEmployeeRequest request, Employee employeeToUpdate) {
        employeeToUpdate.setFirstName(request.getFirstName());
        employeeToUpdate.setLastName(request.getLastName());
        employeeToUpdate.setEmail(request.getEmail());
        employeeToUpdate.setPhoneNumber(request.getPhoneNumber());
        employeeToUpdate.setGender(request.getGender());
    }

    private UpdateEmployeeResponse updatedBuilderResponse(Employee updatedEmployee) {
        return UpdateEmployeeResponse.builder().id(updatedEmployee.getId())
                .message(String.format("Employee with id %s updated Successfully",updatedEmployee.getId()))
                .build();
    }


    @Override
    public List<Employee> getAllEmployee() {

        return  employeeRepository.findAll();
    }



    @Override
    public void delete(Long id) {

    }


}
