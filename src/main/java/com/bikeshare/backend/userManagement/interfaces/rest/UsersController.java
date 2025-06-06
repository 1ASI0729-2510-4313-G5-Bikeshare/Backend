package com.bikeshare.backend.userManagement.interfaces.rest;


import com.bikeshare.backend.userManagement.domain.model.aggregate.UserRoles;
import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;
import com.bikeshare.backend.userManagement.domain.model.queries.*;
import com.bikeshare.backend.userManagement.domain.services.UserRolesCommandService;
import com.bikeshare.backend.userManagement.domain.services.UserRolesQueryService;
import com.bikeshare.backend.userManagement.domain.services.UsersCommandService;
import com.bikeshare.backend.userManagement.domain.services.UsersQueryService;
import com.bikeshare.backend.userManagement.interfaces.rest.resources.CreateUserRolesResource;
import com.bikeshare.backend.userManagement.interfaces.rest.resources.CreateUsersResource;
import com.bikeshare.backend.userManagement.interfaces.rest.resources.UserRolesResource;
import com.bikeshare.backend.userManagement.interfaces.rest.resources.UsersResource;
import com.bikeshare.backend.userManagement.interfaces.rest.transform.CreateUserRolesCommandFromResourceAssembler;
import com.bikeshare.backend.userManagement.interfaces.rest.transform.CreateUsersCommandFromResourceAssembler;
import com.bikeshare.backend.userManagement.interfaces.rest.transform.UserRolesResourceFromEntityAssembler;
import com.bikeshare.backend.userManagement.interfaces.rest.transform.UsersResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name= "Users", description = "Endpoints")
public class UsersController {

    private final UsersCommandService usersCommandService;
    private final UsersQueryService usersQueryService;

    public UsersController(UsersCommandService usersCommandService, UsersQueryService usersQueryService) {
        this.usersCommandService = usersCommandService;
        this.usersQueryService = usersQueryService;
    }


    @PostMapping
    @Operation(
            summary = "Create a user",
            description = "Creates a user the provided data"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "User created"),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            }
    )

    public ResponseEntity<UsersResource> createUser(
            @RequestBody CreateUsersResource resource
    ) {
        var createUsersCommand = CreateUsersCommandFromResourceAssembler
                .toCommandFromResource(resource);

        Optional<Users> users = usersCommandService.handle(createUsersCommand);

        return users.map(
                source -> new ResponseEntity<>(UsersResourceFromEntityAssembler.toResourceFromEntity(source), CREATED)
        ).orElseGet(()->ResponseEntity.badRequest().build());
    }

    @GetMapping("{userId}")
    @Operation(
            summary = "Get a users data by userId",
            description = "Gets a users data with userId param"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "User Found"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    public ResponseEntity<UsersResource> getUsersById(@PathVariable("userId") Long userId){
        Optional<Users> users = usersQueryService.handle(new GetUsersByIdQuery(userId));
        return users.map(
                source -> ResponseEntity.ok(UsersResourceFromEntityAssembler.toResourceFromEntity(source))
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{email}")
    @Operation(
            summary = "Get a users data by email",
            description = "Gets a users data with email param"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "User Found"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    public ResponseEntity<UsersResource> getUsersByEmail(@PathVariable("email") String email){
        Optional<Users> users = usersQueryService.handle(new GetUsersByEmailQuery(email));
        return users.map(
                source -> ResponseEntity.ok(UsersResourceFromEntityAssembler.toResourceFromEntity(source))
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }



    @GetMapping
    public ResponseEntity<List<UsersResource>> getUsers() {
        var users= usersQueryService.handle(new GetAllUsersQuery());
        var usersResources = users.stream().map(source -> UsersResourceFromEntityAssembler.toResourceFromEntity(source)).toList();
        return ResponseEntity.ok(usersResources);
    }
}