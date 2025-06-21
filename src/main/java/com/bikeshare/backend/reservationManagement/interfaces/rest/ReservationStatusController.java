package com.bikeshare.backend.reservationManagement.interfaces.rest;


import com.bikeshare.backend.reservationManagement.domain.model.queries.GetAllReservationStatusQuery;
import com.bikeshare.backend.reservationManagement.domain.model.queries.GetReservationStatusByIdQuery;
import com.bikeshare.backend.reservationManagement.domain.services.ReservationStatusCommandService;
import com.bikeshare.backend.reservationManagement.domain.services.ReservationStatusQueryService;
import com.bikeshare.backend.reservationManagement.interfaces.rest.resources.CreateReservationStatusResource;
import com.bikeshare.backend.reservationManagement.interfaces.rest.resources.ReservationStatusResource;
import com.bikeshare.backend.reservationManagement.interfaces.rest.transform.CreateReservationStatusCommandFromResourceAssembler;
import com.bikeshare.backend.reservationManagement.interfaces.rest.transform.ReservationStatusResourceFromEntityAssembler;
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
public class ReservationStatusController {

    private final ReservationStatusCommandService reservationStatusCommandService;
    private final ReservationStatusQueryService reservationStatusQueryService;

    ReservationStatusController(ReservationStatusCommandService reservationStatusCommandService, ReservationStatusQueryService reservationStatusQueryService) {
        this.reservationStatusCommandService = reservationStatusCommandService;
        this.reservationStatusQueryService = reservationStatusQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a rental status")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Rental Status created"),
                    @ApiResponse(responseCode = "400", description = "Bad Request")
            }
    )
    public ResponseEntity<ReservationStatusResource> createRentalStatus(@RequestBody CreateReservationStatusResource resource){
        var createRentalStatusCommand = CreateReservationStatusCommandFromResourceAssembler.toCommandFromResource(resource);
        var rentalStatus = reservationStatusCommandService.handle(createRentalStatusCommand);
        if (rentalStatus.isEmpty()) return ResponseEntity.badRequest().build();
        var createdRentalStatus = rentalStatus.get();
        var rentalStatusResource = ReservationStatusResourceFromEntityAssembler.toResourceFromEntity(createdRentalStatus);
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
    public ResponseEntity<ReservationStatusResource> getRentalStatusById(@PathVariable("statusId") Long statusId){
        var getRentalStatusByIdQuery = new GetReservationStatusByIdQuery(statusId);
        var rentalStatus = reservationStatusQueryService.handle(getRentalStatusByIdQuery);
        if (rentalStatus.isEmpty()) return ResponseEntity.badRequest().build();
        var rentalStatusEntity = rentalStatus.get();
        var rentalStatusResource = ReservationStatusResourceFromEntityAssembler.toResourceFromEntity(rentalStatusEntity);
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
    public ResponseEntity<List<ReservationStatusResource>> getAllRentalStatus(){
        var rentalStatus = reservationStatusQueryService.handle(new GetAllReservationStatusQuery());
        if (rentalStatus.isEmpty()) return ResponseEntity.notFound().build();
        var rentalStatusResource = rentalStatus.stream()
                .map(ReservationStatusResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(rentalStatusResource);
    }
}
