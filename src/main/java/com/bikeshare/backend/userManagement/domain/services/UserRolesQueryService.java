package com.bikeshare.backend.userManagement.domain.services;

import com.bikeshare.backend.reviewFeedback.domain.model.queries.GetAllReviewsQuery;
import com.bikeshare.backend.userManagement.domain.model.aggregate.UserRoles;
import com.bikeshare.backend.userManagement.domain.model.queries.GetAllUserRolesQuery;
import com.bikeshare.backend.userManagement.domain.model.queries.GetUserRolesByIdQuery;

import java.util.List;
import java.util.Optional;

public interface UserRolesQueryService {
    Optional<UserRoles> handle(GetUserRolesByIdQuery query);

    List<UserRoles> handle(GetAllUserRolesQuery query);
}
