package com.bikeshare.backend.bikeInventory.interfaces.rest;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.BikeStatus;
import com.bikeshare.backend.bikeInventory.domain.model.aggregate.BikeTypes;
import com.bikeshare.backend.bikeInventory.domain.model.queries.GetAllBikeStatusQuery;
import com.bikeshare.backend.bikeInventory.domain.model.queries.GetAllBikeTypesQuery;
import com.bikeshare.backend.bikeInventory.domain.model.queries.GetBikeStatusByIdQuery;
import com.bikeshare.backend.bikeInventory.domain.model.queries.GetBikeTypesByIdQuery;
import com.bikeshare.backend.bikeInventory.domain.services.BikeStatusCommandService;
import com.bikeshare.backend.bikeInventory.domain.services.BikeStatusQueryService;
import com.bikeshare.backend.bikeInventory.domain.services.BikeTypesCommandService;
import com.bikeshare.backend.bikeInventory.domain.services.BikeTypesQueryService;
import com.bikeshare.backend.bikeInventory.interfaces.rest.resources.BikeStatusResource;
import com.bikeshare.backend.bikeInventory.interfaces.rest.resources.BikeTypesResource;
import com.bikeshare.backend.bikeInventory.interfaces.rest.resources.CreateBikeStatusResource;
import com.bikeshare.backend.bikeInventory.interfaces.rest.resources.CreateBikeTypesResource;
import com.bikeshare.backend.bikeInventory.interfaces.rest.transform.BikeStatusResourceFromEntityAssembler;
import com.bikeshare.backend.bikeInventory.interfaces.rest.transform.BikeTypesResourceFromEntityAssembler;
import com.bikeshare.backend.bikeInventory.interfaces.rest.transform.CreateBikeStatusCommandFromResourceAssembler;
import com.bikeshare.backend.bikeInventory.interfaces.rest.transform.CreateBikeTypesCommandFromResourceAssembler;
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
@RequestMapping(value = "/api/v1/bike-types", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Bike Types", description = "Endpoints")
public class BikeTypesController {
    private final BikeTypesCommandService bikeTypesCommandService;
    private final BikeTypesQueryService bikeTypesQueryService;

    public BikeTypesController(BikeTypesCommandService bikeTypesCommandService, BikeTypesQueryService bikeTypesQueryService) {
        this.bikeTypesCommandService = bikeTypesCommandService;
        this.bikeTypesQueryService = bikeTypesQueryService;
    }

    @PostMapping
    @Operation(
            summary = "Creates a bike type",
            description = "Creates a bike type with the provided type name"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Bike Type created"),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            }
    )

    public ResponseEntity<BikeTypesResource> createBikeType(
            @RequestBody CreateBikeTypesResource resource
    ){
        var createBikeTypeCommand = CreateBikeTypesCommandFromResourceAssembler
                .toCommandFromResource(resource);

        Optional<BikeTypes> bikeTypes = bikeTypesCommandService.handle(createBikeTypeCommand);

        return bikeTypes.map(
                source -> new ResponseEntity<>(BikeTypesResourceFromEntityAssembler.toResourceFromEntity(source), CREATED)
        ).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("{statusId}")
    @Operation(
            summary = "Get a bike type by Id",
            description = "Get a bike type with Id param"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Bike Type found"),
                    @ApiResponse(responseCode = "404", description = " Not Found")
            }
    )
    public ResponseEntity<BikeTypesResource> getBikeTypeById(@PathVariable Long typeId) {
        Optional<BikeTypes> bikeTypes = bikeTypesQueryService.handle(new GetBikeTypesByIdQuery(typeId));
        return bikeTypes.map(
                source -> ResponseEntity.ok(BikeTypesResourceFromEntityAssembler.toResourceFromEntity(source))
        ).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<BikeTypesResource>> getAllBikeTypes() {
        var bikeTypes = bikeTypesQueryService.handle(new GetAllBikeTypesQuery());
        var bikeTypesResources = bikeTypes.stream().map(source -> BikeTypesResourceFromEntityAssembler.toResourceFromEntity(source)).toList();
        return ResponseEntity.ok(bikeTypesResources);
    }
}
