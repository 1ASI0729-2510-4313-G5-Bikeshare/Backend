package com.bikeshare.backend.lenderManagement.interfaces.rest;

import com.bikeshare.backend.lenderManagement.domain.model.queries.GetAllLenderProfilesQuery;
import com.bikeshare.backend.lenderManagement.domain.model.queries.GetLenderProfileById;
import com.bikeshare.backend.lenderManagement.domain.model.queries.GetLenderProfileByTotalEarnings;
import com.bikeshare.backend.lenderManagement.domain.services.LenderProfileCommandService;
import com.bikeshare.backend.lenderManagement.domain.services.LenderProfileQueryService;
import com.bikeshare.backend.lenderManagement.interfaces.rest.resources.CreateLenderProfileResource;
import com.bikeshare.backend.lenderManagement.interfaces.rest.resources.LenderProfileResource;
import com.bikeshare.backend.lenderManagement.interfaces.rest.transform.CreateLenderProfileCommandFromResourceAssembler;
import com.bikeshare.backend.lenderManagement.interfaces.rest.transform.LenderProfileResourceFromEntityAssembler;
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
@Tag(name = "Lender Profiles", description = "Lender Profile Endpoints")
public class LenderProfileController {

    private final LenderProfileCommandService lenderProfileCommandService;
    private final LenderProfileQueryService lenderProfileQueryService;

    LenderProfileController(LenderProfileCommandService lenderProfileCommandService, LenderProfileQueryService lenderProfileQueryService) {
        this.lenderProfileCommandService = lenderProfileCommandService;
        this.lenderProfileQueryService = lenderProfileQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a lender profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lender Profile created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<LenderProfileResource> createLenderProfile(@RequestBody CreateLenderProfileResource resource) {
        var createLenderProfileCommand = CreateLenderProfileCommandFromResourceAssembler.toCommandFromResource(resource);
        var lenderProfile = lenderProfileCommandService.handle(createLenderProfileCommand);
        if (lenderProfile.isEmpty()) return ResponseEntity.badRequest().build();
        var createdProfile = lenderProfile.get();
        var lenderProfileResource = LenderProfileResourceFromEntityAssembler.toResourceFromEntity(createdProfile);
        return new ResponseEntity<>(lenderProfileResource, HttpStatus.CREATED);
    }

    @GetMapping("/{lenderProfileId}")
    @Operation(summary = "Get lender profile by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile Found"),
            @ApiResponse(responseCode = "404", description = "Profile not Found")
    })
    public ResponseEntity<LenderProfileResource> getLenderProfileById(@PathVariable Long lenderProfileId) {
        var getLenderProfileByIdQuery = new GetLenderProfileById(lenderProfileId);
        var lenderProfile = lenderProfileQueryService.handle(getLenderProfileByIdQuery);
        if (lenderProfile.isEmpty()) return ResponseEntity.notFound().build();
        var lenderProfileEntity = lenderProfile.get();
        var lenderProfileResource = LenderProfileResourceFromEntityAssembler.toResourceFromEntity(lenderProfileEntity);
        return ResponseEntity.ok(lenderProfileResource);
    }


    @GetMapping("/{totalEarnings}")
    @Operation(summary = "Get profiles by total earnings")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "LenderProfile Found"),
            @ApiResponse(responseCode = "404", description = "LenderProfile not Found")
    })
    public ResponseEntity<List<LenderProfileResource>> getLenderProfileById(@PathVariable Double totalEarnings) {
        var getLenderProfilesByTotalEarnings = new GetLenderProfileByTotalEarnings(totalEarnings);
        var lenderProfiles = lenderProfileQueryService.handle(getLenderProfilesByTotalEarnings);
        if (lenderProfiles.isEmpty()) return ResponseEntity.notFound().build();
        var lenderProfileResource = lenderProfiles.stream()
                .map(LenderProfileResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(lenderProfileResource);
    }

    @GetMapping
    @Operation(summary = "Get all lender profiles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lender Profile Found"),
            @ApiResponse(responseCode = "404", description = "Lender Profile not Found")
    })
    public ResponseEntity<List<LenderProfileResource>> getAllLenderProfiles() {
        var lenderProfiles = lenderProfileQueryService.handle(new GetAllLenderProfilesQuery());
        if (lenderProfiles.isEmpty()) return ResponseEntity.notFound().build();
        var lenderProfileResource = lenderProfiles.stream()
                .map(LenderProfileResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(lenderProfileResource);
    }

}
