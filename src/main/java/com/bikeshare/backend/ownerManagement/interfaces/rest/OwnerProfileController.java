package com.bikeshare.backend.ownerManagement.interfaces.rest;

import com.bikeshare.backend.ownerManagement.domain.model.queries.GetAllOwnerProfilesQuery;
import com.bikeshare.backend.ownerManagement.domain.model.queries.GetOwnerProfileById;
import com.bikeshare.backend.ownerManagement.domain.model.queries.GetOwnerProfileByTotalEarnings;
import com.bikeshare.backend.ownerManagement.domain.services.OwnerProfileCommandService;
import com.bikeshare.backend.ownerManagement.domain.services.OwnerProfileQueryService;
import com.bikeshare.backend.ownerManagement.interfaces.rest.resources.CreateOwnerProfileResource;
import com.bikeshare.backend.ownerManagement.interfaces.rest.resources.OwnerProfileResource;
import com.bikeshare.backend.ownerManagement.interfaces.rest.transform.CreateOwnerProfileCommandFromResourceAssembler;
import com.bikeshare.backend.ownerManagement.interfaces.rest.transform.OwnerProfileResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/owner-profile", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Owner Profiles", description = "Owner Profile Endpoints")
public class OwnerProfileController {

    private final OwnerProfileCommandService ownerProfileCommandService;
    private final OwnerProfileQueryService ownerProfileQueryService;

    OwnerProfileController(OwnerProfileCommandService ownerProfileCommandService, OwnerProfileQueryService ownerProfileQueryService) {
        this.ownerProfileCommandService = ownerProfileCommandService;
        this.ownerProfileQueryService = ownerProfileQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a Owner profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lender Profile created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<OwnerProfileResource> createOwnerProfile(@RequestBody CreateOwnerProfileResource resource) {
        var createLenderProfileCommand = CreateOwnerProfileCommandFromResourceAssembler.toCommandFromResource(resource);
        var ownerProfile = ownerProfileCommandService.handle(createLenderProfileCommand);
        if (ownerProfile.isEmpty()) return ResponseEntity.badRequest().build();
        var createdProfile = ownerProfile.get();
        var ownerProfileResource = OwnerProfileResourceFromEntityAssembler.toResourceFromEntity(createdProfile);
        return new ResponseEntity<>(ownerProfileResource, HttpStatus.CREATED);
    }

    @GetMapping("/{ownerProfileId}")
    @Operation(summary = "Get Owner profile by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile Found"),
            @ApiResponse(responseCode = "404", description = "Profile not Found")
    })
    public ResponseEntity<OwnerProfileResource> getOwnerProfileById(@PathVariable Long ownerProfileId) {
        var getOwnerProfileByIdQuery = new GetOwnerProfileById(ownerProfileId);
        var ownerProfile = ownerProfileQueryService.handle(getOwnerProfileByIdQuery);
        if (ownerProfile.isEmpty()) return ResponseEntity.notFound().build();
        var ownerProfileEntity = ownerProfile.get();
        var ownerProfileResource = OwnerProfileResourceFromEntityAssembler.toResourceFromEntity(ownerProfileEntity);
        return ResponseEntity.ok(ownerProfileResource);
    }


    @GetMapping("/by-earnings/{totalEarnings}")
    @Operation(summary = "Get profiles by total earnings")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profiles Found"),
            @ApiResponse(responseCode = "404", description = "Profiles not Found")
    })
    public ResponseEntity<List<OwnerProfileResource>> getOwnerProfilesByEarnings(@PathVariable Double totalEarnings) {
        var getOwnerProfilesByTotalEarnings = new GetOwnerProfileByTotalEarnings(totalEarnings);
        var ownerProfiles = ownerProfileQueryService.handle(getOwnerProfilesByTotalEarnings);
        if (ownerProfiles.isEmpty()) return ResponseEntity.notFound().build();
        var ownerProfileResources = ownerProfiles.stream()
                .map(OwnerProfileResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(ownerProfileResources);
    }


    @GetMapping
    @Operation(summary = "Get all Owner profiles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Owner Profiles Found"),
            @ApiResponse(responseCode = "404", description = "Owner Profiles not Found")
    })
    public ResponseEntity<List<OwnerProfileResource>> getAllOwnerProfiles() {
        var ownerProfiles = ownerProfileQueryService.handle(new GetAllOwnerProfilesQuery());
        if (ownerProfiles.isEmpty()) return ResponseEntity.notFound().build();
        var ownerProfileResources = ownerProfiles.stream()
                .map(OwnerProfileResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(ownerProfileResources);
    }

}
