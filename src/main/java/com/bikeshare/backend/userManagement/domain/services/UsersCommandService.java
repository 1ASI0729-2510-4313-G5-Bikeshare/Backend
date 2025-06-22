package com.bikeshare.backend.userManagement.domain.services;

import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;
import com.bikeshare.backend.userManagement.domain.model.commands.CreateUsersCommand;

import java.util.Optional;

public interface UsersCommandService {
    Optional<Users> handle(CreateUsersCommand command);
}
