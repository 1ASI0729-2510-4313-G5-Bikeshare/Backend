package com.bikeshare.backend.userManagement.application.internal.queryservices;

import com.bikeshare.backend.userManagement.domain.model.aggregate.UserRoles;
import com.bikeshare.backend.userManagement.domain.model.queries.GetAllUserRolesQuery;
import com.bikeshare.backend.userManagement.domain.model.queries.GetUserRolesByIdQuery;
import com.bikeshare.backend.userManagement.domain.services.UserRolesQueryService;
import com.bikeshare.backend.userManagement.infrastructure.persistance.jpa.UserRolesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRolesQueryServiceImpl implements UserRolesQueryService {

    private final UserRolesRepository userRolesRepository;

    UserRolesQueryServiceImpl(UserRolesRepository userRolesRepository) {
        this.userRolesRepository = userRolesRepository;
    }

    @Override
    public Optional<UserRoles> handle(GetUserRolesByIdQuery query) {
        return this.userRolesRepository.findById(query.role_id());
    }

    @Override
    public List<UserRoles> handle(GetAllUserRolesQuery query) {

        return this.userRolesRepository.findAll();
    }
}
