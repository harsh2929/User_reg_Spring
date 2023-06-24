package com.employeeregistry.services.impl;

import com.employeeregistry.dto.UserDTO;
import com.employeeregistry.dto.UserLoginDTO;
import com.employeeregistry.entity.EmployeeEntity;
import com.employeeregistry.entity.SignUpEntity;
import com.employeeregistry.exceptions.EmployeeExistsException;
import com.employeeregistry.exceptions.EmployeeNotFoundException;
import com.employeeregistry.exceptions.UnAuthorizedException;
import com.employeeregistry.exceptions.UserExistsException;
import com.employeeregistry.repository.EmployeeRepository;
import com.employeeregistry.repository.SignUpRepository;
import com.employeeregistry.services.EmployeeRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeRegistryServiceImpl implements EmployeeRegistryService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SignUpRepository signUpRepository;

    @Override
    public UserDTO getUser(String userId) {
        SignUpEntity signUpEntity = signUpRepository.findByUserId(userId);
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(signUpEntity.getEmail());
        userDTO.setName(signUpEntity.getUserName());
        userDTO.setUserId(userId);
        return userDTO;
    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) throws UnAuthorizedException {
        SignUpEntity signUpEntity = signUpRepository.findByUserIdAndPassword(userLoginDTO.getUserId(), userLoginDTO.getPassword());
        if (signUpEntity == null) {
            throw new UnAuthorizedException("Invalid username or password");
        }
        return true;
    }

    @Override
    public boolean signUp(SignUpEntity signUpEntity) throws UserExistsException {
        SignUpEntity checkedUserEntity = signUpRepository.findByUserId(signUpEntity.getUserId());
        signUpEntity.setCreatedTime(LocalDateTime.now());
        if (checkedUserEntity != null) {
            throw new UserExistsException("User with ID: " + signUpEntity.getUserId() + " already exists");
        } else {
            signUpRepository.save(signUpEntity);
        }
        return true;
    }

    @Override
    public EmployeeEntity createEmployee(EmployeeEntity employeeEntity) throws EmployeeExistsException {
        EmployeeEntity checkedEmployee = employeeRepository.findByEmployeeId(employeeEntity.getEmployeeId());
        if (checkedEmployee != null) {
            throw new EmployeeExistsException("Employee with ID: " + employeeEntity.getEmployeeId() + " already exists");
        }
        employeeEntity.setCreatedTime(LocalDateTime.now());
        employeeEntity.setModifiedTime(LocalDateTime.now());
        return employeeRepository.save(employeeEntity);
    }

    @Override
    public EmployeeEntity updateEmployee(EmployeeEntity employeeEntity) {
        employeeEntity.setModifiedTime(LocalDateTime.now());
        return employeeRepository.save(employeeEntity);
    }

    @Override
    public EmployeeEntity getEmployee(String employeeId) throws EmployeeNotFoundException {
        EmployeeEntity employeeEntity = employeeRepository.findByEmployeeId(employeeId);
        if (employeeEntity == null) {
            throw new EmployeeNotFoundException("Employee with ID: " + employeeId + " not found");
        }
        return employeeEntity;
    }

    @Override
    public Page<EmployeeEntity> getEmployeesByPage(Pageable page) {
        return employeeRepository.findAll(page);
    }
}
