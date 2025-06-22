package com.bikeshare.backend.userManagement.interfaces.rest.transform;

import com.bikeshare.backend.userManagement.domain.model.aggregate.UserRoles;
import com.bikeshare.backend.userManagement.interfaces.rest.resources.UserRolesResource;

public class UserRolesResourceFromEntityAssembler {

    public static UserRolesResource toResourceFromEntity(UserRoles entity) {
        return new UserRolesResource(entity.getRoleName());
    }
}
