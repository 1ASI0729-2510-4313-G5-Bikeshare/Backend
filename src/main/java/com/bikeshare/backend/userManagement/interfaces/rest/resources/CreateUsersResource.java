package com.bikeshare.backend.userManagement.interfaces.rest.resources;

import com.bikeshare.backend.userManagement.domain.model.aggregate.UserRoles;

public record CreateUsersResource(String email, String password_hash, String full_name, UserRoles role) {
}
