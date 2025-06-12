package com.bikeshare.backend.rentalOperations.interfaces.rest;


import com.bikeshare.backend.rentalOperations.domain.model.queries.GetAllRentalStatusQuery;
import com.bikeshare.backend.rentalOperations.domain.model.queries.GetRentalStatusByIdQuery;
import com.bikeshare.backend.rentalOperations.domain.services.RentalStatusCommandService;
import com.bikeshare.backend.rentalOperations.domain.services.RentalStatusQueryService;
import com.bikeshare.backend.rentalOperations.interfaces.rest.resources.CreateRentalStatusResource;
import com.bikeshare.backend.rentalOperations.interfaces.rest.resources.RentalStatusResource;
import com.bikeshare.backend.rentalOperations.interfaces.rest.transform.CreateRentalStatusCommandFromResourceAssembler;
import com.bikeshare.backend.rentalOperations.interfaces.rest.transform.RentalStatusResourceFromEntityAssembler;
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
@RequestMapping(value = "/api/v1/rental-status", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Rental Status", description = "Available Rental Status Endpoints")
public class RentalStatusController {

    private final RentalStatusCommandService rentalStatusCommandService;
    private final RentalStatusQueryService rentalStatusQueryService;

    RentalStatusController(RentalStatusCommandService rentalStatusCommandService, RentalStatusQueryService rentalStatusQueryService) {
        this.rentalStatusCommandService = rentalStatusCommandService;
        this.rentalStatusQueryService = rentalStatusQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a rental status")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Rental Status created"),
                    @ApiResponse(responseCode = "400", description = "Bad Request")
            }
    )
    public ResponseEntity<RentalStatusResource> createRentalStatus(@RequestBody CreateRentalStatusResource resource){
        var createRentalStatusCommand = CreateRentalStatusCommandFromResourceAssembler.toCommandFromResource(resource);
        var rentalStatus = rentalStatusCommandService.handle(createRentalStatusCommand);
        if (rentalStatus.isEmpty()) return ResponseEntity.badRequest().build();
        var createdRentalStatus = rentalStatus.get();
        var rentalStatusResource = RentalStatusResourceFromEntityAssembler.toResourceFromEntity(createdRentalStatus);
        return new ResponseEntity<>(rentalStatusResource, HttpStatus.CREATED);
    }

    @GetMapping("/{statusId}")
    @Operation(summary = "Get rental status by Id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Rental Status Found"),
                    @ApiResponse(responseCode = "404", description = "Rental Status not Found")
            }
    )
    public ResponseEntity<RentalStatusResource> getRentalStatusById(@PathVariable("statusId") Long statusId){
        var getRentalStatusByIdQuery = new GetRentalStatusByIdQuery(statusId);
        var rentalStatus = rentalStatusQueryService.handle(getRentalStatusByIdQuery);
        if (rentalStatus.isEmpty()) return ResponseEntity.badRequest().build();
        var rentalStatusEntity = rentalStatus.get();
        var rentalStatusResource = RentalStatusResourceFromEntityAssembler.toResourceFromEntity(rentalStatusEntity);
        return ResponseEntity.ok(rentalStatusResource);
    }


    @GetMapping
    @Operation(summary = "Get all rental status")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Rental Status found"),
                    @ApiResponse(responseCode = "404", description = "Rental Status not found")
            }
    )
    public ResponseEntity<List<RentalStatusResource>> getAllRentalStatus(){
        var rentalStatus = rentalStatusQueryService.handle(new GetAllRentalStatusQuery());
        if (rentalStatus.isEmpty()) return ResponseEntity.notFound().build();
        var rentalStatusResource = rentalStatus.stream()
                .map(RentalStatusResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(rentalStatusResource);
    }
}
