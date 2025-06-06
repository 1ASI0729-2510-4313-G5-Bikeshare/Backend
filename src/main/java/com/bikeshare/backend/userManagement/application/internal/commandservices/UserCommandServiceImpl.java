package com.bikeshare.backend.userManagement.application.internal.commandservices;

import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;
import com.bikeshare.backend.userManagement.domain.model.commands.CreateUsersCommand;
import com.bikeshare.backend.userManagement.domain.services.UsersCommandService;
import com.bikeshare.backend.userManagement.infrastructure.persistance.jpa.UserRolesRepository;
import com.bikeshare.backend.userManagement.infrastructure.persistance.jpa.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UsersCommandService {
    private final UsersRepository usersRepository;

    UserCommandServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Optional<Users> handle(CreateUsersCommand command) {
        if(usersRepository.existsByEmail(command.email())){
            throw new IllegalArgumentException("Email already exists");
        }
        var users =new Users(command);
        var createdUsers = usersRepository.save(users);

        return Optional.of(createdUsers);
    }

}
