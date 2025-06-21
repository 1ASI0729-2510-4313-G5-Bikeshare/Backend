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
@RequestMapping(value = "/api/v1/lender-profile", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Owner Profiles", description = "Lender Profile Endpoints")
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
    public ResponseEntity<OwnerProfileResource> createLenderProfile(@RequestBody CreateOwnerProfileResource resource) {
        var createLenderProfileCommand = CreateOwnerProfileCommandFromResourceAssembler.toCommandFromResource(resource);
        var lenderProfile = ownerProfileCommandService.handle(createLenderProfileCommand);
        if (lenderProfile.isEmpty()) return ResponseEntity.badRequest().build();
        var createdProfile = lenderProfile.get();
        var lenderProfileResource = OwnerProfileResourceFromEntityAssembler.toResourceFromEntity(createdProfile);
        return new ResponseEntity<>(lenderProfileResource, HttpStatus.CREATED);
    }

    @GetMapping("/{lenderProfileId}")
    @Operation(summary = "Get Owner profile by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile Found"),
            @ApiResponse(responseCode = "404", description = "Profile not Found")
    })
    public ResponseEntity<OwnerProfileResource> getLenderProfileById(@PathVariable Long lenderProfileId) {
        var getLenderProfileByIdQuery = new GetOwnerProfileById(lenderProfileId);
        var lenderProfile = ownerProfileQueryService.handle(getLenderProfileByIdQuery);
        if (lenderProfile.isEmpty()) return ResponseEntity.notFound().build();
        var lenderProfileEntity = lenderProfile.get();
        var lenderProfileResource = OwnerProfileResourceFromEntityAssembler.toResourceFromEntity(lenderProfileEntity);
        return ResponseEntity.ok(lenderProfileResource);
    }


    @GetMapping("/{totalEarnings}")
    @Operation(summary = "Get profiles by total earnings")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "LenderProfile Found"),
            @ApiResponse(responseCode = "404", description = "LenderProfile not Found")
    })
    public ResponseEntity<List<OwnerProfileResource>> getLenderProfileById(@PathVariable Double totalEarnings) {
        var getLenderProfilesByTotalEarnings = new GetOwnerProfileByTotalEarnings(totalEarnings);
        var lenderProfiles = ownerProfileQueryService.handle(getLenderProfilesByTotalEarnings);
        if (lenderProfiles.isEmpty()) return ResponseEntity.notFound().build();
        var lenderProfileResource = lenderProfiles.stream()
                .map(OwnerProfileResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(lenderProfileResource);
    }

    @GetMapping
    @Operation(summary = "Get all Owner profiles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lender Profile Found"),
            @ApiResponse(responseCode = "404", description = "Lender Profile not Found")
    })
    public ResponseEntity<List<OwnerProfileResource>> getAllLenderProfiles() {
        var lenderProfiles = ownerProfileQueryService.handle(new GetAllOwnerProfilesQuery());
        if (lenderProfiles.isEmpty()) return ResponseEntity.notFound().build();
        var lenderProfileResource = lenderProfiles.stream()
                .map(OwnerProfileResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(lenderProfileResource);
    }

}
