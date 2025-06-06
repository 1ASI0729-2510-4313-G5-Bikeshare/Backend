package com.bikeshare.backend.userManagement.application.internal.queryservices;

import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;
import com.bikeshare.backend.userManagement.domain.model.queries.*;
import com.bikeshare.backend.userManagement.domain.services.UsersQueryService;
import com.bikeshare.backend.userManagement.infrastructure.persistance.jpa.UserRolesRepository;
import com.bikeshare.backend.userManagement.infrastructure.persistance.jpa.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersQueryServiceImpl implements UsersQueryService {

    private final UsersRepository usersRepository;

    UsersQueryServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Optional<Users> handle(GetUsersByIdQuery query) {
        return this.usersRepository.findById(query.userId());
    }

    @Override
    public Optional<Users> handle(GetUsersByEmailQuery query) {
        return this.usersRepository.findByEmail(query.email());
    }

    @Override
    public List<Users> handle(GetAllUsersQuery query) {
        return this.usersRepository.findAll();
    }
}
