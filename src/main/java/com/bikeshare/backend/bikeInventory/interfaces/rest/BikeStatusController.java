package com.bikeshare.backend.bikeInventory.interfaces.rest;


import com.bikeshare.backend.bikeInventory.domain.model.aggregate.BikeStatus;
import com.bikeshare.backend.bikeInventory.domain.model.commands.CreateBikeStatusCommand;
import com.bikeshare.backend.bikeInventory.domain.model.queries.GetAllBikeStatusQuery;
import com.bikeshare.backend.bikeInventory.domain.model.queries.GetBikeStatusByIdQuery;
import com.bikeshare.backend.bikeInventory.domain.services.BikeStatusCommandService;
import com.bikeshare.backend.bikeInventory.domain.services.BikeStatusQueryService;
import com.bikeshare.backend.bikeInventory.interfaces.rest.resources.BikeStatusResource;
import com.bikeshare.backend.bikeInventory.interfaces.rest.resources.CreateBikeStatusResource;
import com.bikeshare.backend.bikeInventory.interfaces.rest.transform.BikeStatusResourceFromEntityAssembler;
import com.bikeshare.backend.bikeInventory.interfaces.rest.transform.CreateBikeStatusCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/api/v1/bike-status", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Bike Status", description = "Endpoints")
public class BikeStatusController {

    private final BikeStatusCommandService bikeStatusCommandService;
    private final BikeStatusQueryService bikeStatusQueryService;

    public BikeStatusController(BikeStatusCommandService bikeStatusCommandService, BikeStatusQueryService bikeStatusQueryService) {
        this.bikeStatusCommandService = bikeStatusCommandService;
        this.bikeStatusQueryService = bikeStatusQueryService;
    }

    @PostMapping
    @Operation(
            summary = "Creates a bike status",
            description = "Creates a bike status with the provided status name"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Bike Status created"),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            }
    )

    public ResponseEntity<BikeStatusResource> createBikeStatus(
            @RequestBody CreateBikeStatusResource resource
    ){
        var createBikeStatusCommand = CreateBikeStatusCommandFromResourceAssembler
                .toCommandFromResource(resource);

        Optional<BikeStatus> bikeStatus = bikeStatusCommandService.handle(createBikeStatusCommand);

        return bikeStatus.map(
                source -> new ResponseEntity<>(BikeStatusResourceFromEntityAssembler.toResourceFromEntity(source), CREATED)
        ).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("{statusId}")
    @Operation(
            summary = "Get a bike status by Id",
            description = "Get a bike status with Id param"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Bike Status found"),
                    @ApiResponse(responseCode = "404", description = " Not Found")
            }
    )
    public ResponseEntity<BikeStatusResource> getBikeStatusById(@PathVariable Long statusId) {
        Optional<BikeStatus> bikeStatus = bikeStatusQueryService.handle(new GetBikeStatusByIdQuery(statusId));
        return bikeStatus.map(
                source -> ResponseEntity.ok(BikeStatusResourceFromEntityAssembler.toResourceFromEntity(source))
        ).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<BikeStatusResource>> getAllBikeStatus() {
        var bikeStatus = bikeStatusQueryService.handle(new GetAllBikeStatusQuery());
        var bikeStatusResources = bikeStatus.stream().map(source -> BikeStatusResourceFromEntityAssembler.toResourceFromEntity(source)).toList();
        return ResponseEntity.ok(bikeStatusResources);
    }
}
