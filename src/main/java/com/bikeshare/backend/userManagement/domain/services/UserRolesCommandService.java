package com.bikeshare.backend.userManagement.domain.services;

import com.bikeshare.backend.userManagement.domain.model.aggregate.UserRoles;
import com.bikeshare.backend.userManagement.domain.model.commands.CreateUserRolesCommand;

import java.util.Optional;

public interface UserRolesCommandService {
    Optional<UserRoles> handle(CreateUserRolesCommand command);
}
