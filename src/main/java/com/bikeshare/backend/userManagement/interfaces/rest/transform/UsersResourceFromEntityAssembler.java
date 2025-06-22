package com.bikeshare.backend.userManagement.interfaces.rest.transform;

import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;
import com.bikeshare.backend.userManagement.interfaces.rest.resources.UsersResource;

public class UsersResourceFromEntityAssembler {
    public static UsersResource toResourceFromEntity(Users entity) {
        return new UsersResource(entity.getEmail(),entity.getPassword_hash(), entity.getFull_name(), entity.getRole());
    }
}
