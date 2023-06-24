package com.employeeregistry.repository;

import com.employeeregistry.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<EmployeeEntity, String> {
    EmployeeEntity findByEmployeeId(String employeeId);

    @Query("SELECT m FROM EmployeeEntity m WHERE m.employeeId LIKE %:employeeId% or m.lastName LIKE %:lastName% or m.firstName LIKE %:firstName% ")
    List<EmployeeEntity> findByEmployeeIdLikeOrFirstNameLikeOrLastNameLike(@Param("employeeId") String employeeId, @Param("firstName") String firstName, @Param("lastName") String lastName);

    Page<EmployeeEntity> findAll(Pageable pageable);
}
