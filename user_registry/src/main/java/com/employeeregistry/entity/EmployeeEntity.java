package com.employeeregistry.entity;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "employee_details")
@Data
public class EmployeeEntity {
    @Id
    @Column(name = "employee_id")
    private String employeeId;

    @NotBlank
    @Column(name = "employee_first_name")
    private String firstName;

    @NotBlank
    @Column(name = "employee_last_name")
    private String lastName;

    @URL
    @Column(name = "employee_profile_url")
    private String employeeProfileUrl;

    @NotBlank
    @Email
    @Column(name = "employee_email")
    private String email;

    @NotBlank
    @Column(name = "employee_phone")
    private String phone;

    @NotBlank
    @Column(name = "crt_by")
    private String createdBy;

    @Column(name = "crt_ts")
    private LocalDateTime createdTime;

    @Column(name = "mod_by")
    private String modifiedBy;

    @Column(name = "mod_ts")
    private LocalDateTime modifiedTime;
}
