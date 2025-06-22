package com.bikeshare.backend.userManagement.application.internal.commandservices;

import com.bikeshare.backend.userManagement.domain.model.aggregate.UserRoles;
import com.bikeshare.backend.userManagement.domain.model.commands.CreateUserRolesCommand;
import com.bikeshare.backend.userManagement.domain.services.UserRolesCommandService;
import com.bikeshare.backend.userManagement.infrastructure.persistance.jpa.UserRolesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRolesCommandServiceImpl implements UserRolesCommandService {

    private final UserRolesRepository userRolesRepository;

    UserRolesCommandServiceImpl(UserRolesRepository userRolesRepository) {
        this.userRolesRepository = userRolesRepository;
    }

    @Override
    public Optional<UserRoles> handle(CreateUserRolesCommand command) {
        if(userRolesRepository.existsByRoleName(command.role_name())){
            throw new IllegalArgumentException("Role already exists");
        }
        var  userRoles = new UserRoles(command);
        var createdUserRoles = userRolesRepository.save(userRoles);

        return Optional.of(createdUserRoles);
    }
}
