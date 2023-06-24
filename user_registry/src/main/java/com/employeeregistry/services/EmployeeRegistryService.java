package com.employeeregistry.services;

import com.employeeregistry.dto.UserDTO;
import com.employeeregistry.dto.UserLoginDTO;
import com.employeeregistry.entity.EmployeeEntity;
import com.employeeregistry.entity.SignUpEntity;
import com.employeeregistry.exceptions.EmployeeNotFoundException;
import com.employeeregistry.exceptions.UnAuthorizedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service interface for managing employee registry.
 */
public interface EmployeeRegistryService {

    /**
     * Get the user details by user ID.
     *
     * @param userId the user ID
     * @return the user DTO
     */
    UserDTO getUserDTO(String userId);

    /**
     * Perform user login with the provided credentials.
     *
     * @param userLoginDTO the user login DTO containing user ID and password
     * @return true if the login is successful, false otherwise
     * @throws UnAuthorizedException if the login is unsuccessful
     */
    boolean login(UserLoginDTO userLoginDTO) throws UnAuthorizedException;

    /**
     * Register a new user.
     *
     * @param signUpEntity the sign up entity containing user details
     * @return true if the sign up is successful, false otherwise
     * @throws RuntimeException if a user with the same ID already exists
     */
    boolean signUp(SignUpEntity signUpEntity);

    /**
     * Create a new employee.
     *
     * @param employeeEntity the employee entity to create
     * @return the created employee entity
     * @throws RuntimeException if an employee with the same ID already exists
     */
    EmployeeEntity createEmployee(EmployeeEntity employeeEntity);

    /**
     * Update an existing employee.
     *
     * @param employeeEntity the employee entity to update
     * @return the updated employee entity
     */
    EmployeeEntity updateEmployee(EmployeeEntity employeeEntity);

    /**
     * Get an employee by employee ID.
     *
     * @param employeeId the employee ID
     * @return the employee entity
     * @throws EmployeeNotFoundException if no employee with the specified ID is found
     */
    EmployeeEntity getEmployeeEntity(String employeeId) throws EmployeeNotFoundException;

    /**
     * Search for employees based on a keyword.
     *
     * @param keyword the keyword to search for
     * @return the list of matching employee entities
     */
    List<EmployeeEntity> searchEmployees(String keyword);

    /**
     * Get a page of employees.
     *
     * @param page the page information
     * @return the page of employee entities
     */
    Page<EmployeeEntity> getEmployeesByPage(Pageable page);

}
