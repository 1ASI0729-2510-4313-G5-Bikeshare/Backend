package com.bikeshare.backend.userManagement.domain.model.commands;

import com.bikeshare.backend.userManagement.domain.model.aggregate.UserRoles;

public record UpdateUserCommand(String email, String password_hash, String full_name) {


}
