package com.bikeshare.backend.userManagement.domain.model.commands;

public record CreateUserRolesCommand(String role_name){

    public CreateUserRolesCommand{
        if (role_name == null || role_name.isEmpty()){
            throw new IllegalArgumentException("role_name is null or empty");
        }
    }
}
