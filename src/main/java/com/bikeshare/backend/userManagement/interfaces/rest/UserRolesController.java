package com.bikeshare.backend.userManagement.interfaces.rest;
import com.bikeshare.backend.userManagement.domain.model.aggregate.UserRoles;
import com.bikeshare.backend.userManagement.domain.model.queries.GetAllUserRolesQuery;
import com.bikeshare.backend.userManagement.domain.model.queries.GetUserRolesByIdQuery;
import com.bikeshare.backend.userManagement.interfaces.rest.resources.CreateUserRolesResource;
import com.bikeshare.backend.userManagement.interfaces.rest.resources.UserRolesResource;
import com.bikeshare.backend.userManagement.interfaces.rest.transform.CreateUserRolesCommandFromResourceAssembler;
import com.bikeshare.backend.userManagement.interfaces.rest.transform.UserRolesResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.bikeshare.backend.userManagement.domain.services.UserRolesCommandService;
import com.bikeshare.backend.userManagement.domain.services.UserRolesQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;


@RestController
@RequestMapping(value = "/api/v1/user-roles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name= "User Roles", description = "Endpoints")
public class UserRolesController {

    private final UserRolesCommandService userRolesCommandService;
    private final UserRolesQueryService userRolesQueryService;

    public UserRolesController(UserRolesCommandService userRolesCommandService, UserRolesQueryService userRolesQueryService) {
        this.userRolesCommandService = userRolesCommandService;
        this.userRolesQueryService = userRolesQueryService;
    }


    @PostMapping
    @Operation(
            summary = "Create a user role",
            description = "Creates a user role with the provided role name"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "User Role created"),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            }
    )

    public ResponseEntity<UserRolesResource> createUserRoles(
            @RequestBody CreateUserRolesResource resource
    ) {
        var createUserRoleCommand = CreateUserRolesCommandFromResourceAssembler
                .toCommandFromResource(resource);

        Optional<UserRoles> userRoles = userRolesCommandService.handle(createUserRoleCommand);

        return userRoles.map(
                source -> new ResponseEntity<>(UserRolesResourceFromEntityAssembler.toResourceFromEntity(source), CREATED)
        ).orElseGet(()->ResponseEntity.badRequest().build());
    }

    @GetMapping("{role_id}")
    @Operation(
            summary = "Get a role name by ID",
            description = "Gets a role name with ID param"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "User Role Found"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    public ResponseEntity<UserRolesResource> getUserRolesById(@PathVariable("role_id") Long role_id){
        Optional<UserRoles> userRoles = userRolesQueryService.handle(new GetUserRolesByIdQuery(role_id));
        return userRoles.map(
                 source -> ResponseEntity.ok(UserRolesResourceFromEntityAssembler.toResourceFromEntity(source))
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping
    public ResponseEntity<List<UserRolesResource>> getUserRoles() {
        var userRoles= userRolesQueryService.handle(new GetAllUserRolesQuery());
        var userRolesResources = userRoles.stream().map(source -> UserRolesResourceFromEntityAssembler.toResourceFromEntity(source)).toList();
        return ResponseEntity.ok(userRolesResources);
    }

}
