package com.employeeregistry.repository;

import com.employeeregistry.entity.SignUpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignUpRepository extends JpaRepository<SignUpEntity,String> {
    SignUpEntity findByUserIdAndPassword(String userId, String password);
    SignUpEntity findByUserId(String userId);
}
