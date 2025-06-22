package com.bikeshare.backend.userManagement.domain.model.aggregate;

import com.bikeshare.backend.userManagement.domain.model.commands.CreateUserRolesCommand;
import jakarta.persistence.*;
import lombok.Getter;

import javax.annotation.processing.Generated;
@Entity
@Getter
public class UserRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Long role_id;

    @Column(name = "role_name",nullable = false)
    private String roleName;

    protected UserRoles() {}

    public UserRoles(CreateUserRolesCommand command) {
        this.roleName = command.role_name();
    }

}

