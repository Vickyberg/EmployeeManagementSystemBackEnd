package com.EmployeeManagementSystem.volacode.controllers;


import com.EmployeeManagementSystem.volacode.dtos.requests.AddEmployeeRequest;
import com.EmployeeManagementSystem.volacode.exceptions.ResourceNotFoundException;
import com.EmployeeManagementSystem.volacode.models.Employee;
import com.EmployeeManagementSystem.volacode.repositories.EmployeeRepository;
import com.EmployeeManagementSystem.volacode.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class EmployeeController {



    private EmployeeRepository employeeRepository;

    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<?> getAllEmployee(){
        return  ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @PostMapping("/add_employee")
    public ResponseEntity<?> addEmployee(@RequestBody AddEmployeeRequest request){
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.add(request));
    }

    @PutMapping("/update_employee/{id}")
    public  ResponseEntity<?> updateEmployee(@PathVariable("id") Long id,@RequestBody AddEmployeeRequest request){

        try {
                employeeService.update(id,request);
            return new ResponseEntity<>(String.format("Employee with id %s updated successfully", id),HttpStatus.OK);
        }
         catch (ResourceNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
         }
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Employee with id %s does not exist ",id)));
        return ResponseEntity.ok(employee);
    }

}
