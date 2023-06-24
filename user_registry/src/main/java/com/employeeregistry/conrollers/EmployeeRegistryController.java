package com.employeeregistry.conrollers;

import com.employeeregistry.dto.PageDTO;
import com.employeeregistry.dto.UserLoginDTO;
import com.employeeregistry.entity.EmployeeEntity;
import com.employeeregistry.entity.SignUpEntity;
import com.employeeregistry.exceptions.EmployeeExistsException;
import com.employeeregistry.exceptions.EmployeeNotFoundException;
import com.employeeregistry.exceptions.UnAuthorizedException;
import com.employeeregistry.exceptions.UserExistsException;
import com.employeeregistry.services.EmployeeRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class EmployeeRegistryController {

    private final EmployeeRegistryService employeeRegistryService;

    @Autowired
    public EmployeeRegistryController(EmployeeRegistryService employeeRegistryService) {
        this.employeeRegistryService = employeeRegistryService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserLoginDTO userLoginDTO) throws UnAuthorizedException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeRegistryService.login(userLoginDTO));
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@RequestBody SignUpEntity signUpEntity) throws UserExistsException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeRegistryService.signUp(signUpEntity));
    }

    @PostMapping("/employees")
    public ResponseEntity<Object> createEmployee(@RequestBody EmployeeEntity employeeEntity) throws EmployeeExistsException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeRegistryService.createEmployee(employeeEntity));
    }

    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<Object> updateEmployee(@PathVariable String employeeId, @RequestBody EmployeeEntity employeeEntity) throws EmployeeNotFoundException {
        EmployeeEntity existingEmployee = employeeRegistryService.getEmployee(employeeId);
        employeeEntity.setCreatedTime(existingEmployee.getCreatedTime());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeRegistryService.updateEmployee(employeeEntity));
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<Object> getEmployee(@PathVariable String employeeId) throws EmployeeNotFoundException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeRegistryService.getEmployee(employeeId));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable String userId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeRegistryService.getUser(userId));
    }

    @PostMapping("/employees/search")
    public ResponseEntity<Object> searchEmployees(@RequestBody String keyword) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeRegistryService.searchEmployees(keyword));
    }

    @PostMapping("/employees/page")
    public ResponseEntity<Object> getEmployeesByPage(@RequestBody PageDTO page) {
        Pageable pageable = PageRequest.of(page.getPage(), page.getSize());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeRegistryService.getEmployeesByPage(pageable));
    }

}
