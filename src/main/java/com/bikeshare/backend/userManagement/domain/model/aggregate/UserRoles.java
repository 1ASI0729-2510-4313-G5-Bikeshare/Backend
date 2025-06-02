package com.bikeshare.backend.userManagement.domain.model.aggregate;

import jakarta.persistence.*;
import lombok.Getter;

import javax.annotation.processing.Generated;
@Entity
@Getter
public class UserRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long role_id;

    @Column(nullable = false)
    private String role_name;

}

