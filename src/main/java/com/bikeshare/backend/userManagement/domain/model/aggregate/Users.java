package com.bikeshare.backend.userManagement.domain.model.aggregate;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private  String email;

    @Column(nullable = false)
    private String password_Hash;

    @Column(nullable = false)
    private String full_Name;

    @Column(nullable = false)
    private int role_id;

    @CreatedDate
    private Date created_At;

    @LastModifiedDate
    private Date updated_At;

}
