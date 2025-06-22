package com.bikeshare.backend.userManagement.domain.services;

import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;
import com.bikeshare.backend.userManagement.domain.model.queries.*;
import org.apache.catalina.User;

import java.util.List;
import java.util.Optional;
import java.util.Spliterator;

public interface UsersQueryService {
    Optional<Users> handle(GetUsersByIdQuery query);

    Optional<Users> handle(GetUsersByEmailQuery query);

    List<Users> handle(GetAllUsersQuery query);
}
