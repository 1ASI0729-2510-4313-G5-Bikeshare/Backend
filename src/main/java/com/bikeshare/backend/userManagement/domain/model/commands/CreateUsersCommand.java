package com.bikeshare.backend.userManagement.domain.model.commands;

import com.bikeshare.backend.userManagement.domain.model.aggregate.UserRoles;

public record CreateUsersCommand(String email, String password_hash, String full_name, UserRoles role) {

    public CreateUsersCommand{
        if (email == null || email.isEmpty()){
            throw new IllegalArgumentException("email cannot be null or empty");
        }
        if (password_hash == null || password_hash.isEmpty()){
            throw new IllegalArgumentException("password_hash cannot be null or empty");
        }
        if (full_name == null || full_name.isEmpty()){
            throw new IllegalArgumentException("full_name cannot be null or empty");
        }
        if (role == null){
            throw new IllegalArgumentException("role cannot be null");
        }
    }
}
