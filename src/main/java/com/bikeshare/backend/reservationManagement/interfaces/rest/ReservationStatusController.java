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
@RequestMapping(value = "/api/v1/reservation-statuses", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Reservation Status", description = "Available Reservation Status Endpoints")
public class ReservationStatusController {

    private final ReservationStatusCommandService reservationStatusCommandService;
    private final ReservationStatusQueryService reservationStatusQueryService;

    ReservationStatusController(ReservationStatusCommandService reservationStatusCommandService, ReservationStatusQueryService reservationStatusQueryService) {
        this.reservationStatusCommandService = reservationStatusCommandService;
        this.reservationStatusQueryService = reservationStatusQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a reservation newStatus")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Reservation Status created"),
                    @ApiResponse(responseCode = "400", description = "Bad Request")
            }
    )
    public ResponseEntity<ReservationStatusResource> createReservationStatus(@RequestBody CreateReservationStatusResource resource){
        var createReservationStatusCommand = CreateReservationStatusCommandFromResourceAssembler.toCommandFromResource(resource);
        var reservationStatus = reservationStatusCommandService.handle(createReservationStatusCommand);
        if (reservationStatus.isEmpty()) return ResponseEntity.badRequest().build();
        var createdReservationStatus = reservationStatus.get();
        var reservationStatusResource = ReservationStatusResourceFromEntityAssembler.toResourceFromEntity(createdReservationStatus);
        return new ResponseEntity<>(reservationStatusResource, HttpStatus.CREATED);
    }

    @GetMapping("/{statusId}")
    @Operation(summary = "Get reservation newStatus by Id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Reservation Status Found"),
                    @ApiResponse(responseCode = "404", description = "Reservation Status not Found")
            }
    )
    public ResponseEntity<ReservationStatusResource> getReservationStatusById(@PathVariable("statusId") Long statusId){
        var getReservationStatusByIdQuery = new GetReservationStatusByIdQuery(statusId);
        var reservationStatus = reservationStatusQueryService.handle(getReservationStatusByIdQuery);
        if (reservationStatus.isEmpty()) return ResponseEntity.badRequest().build();
        var reservationStatusEntity = reservationStatus.get();
        var reservationStatusResource = ReservationStatusResourceFromEntityAssembler.toResourceFromEntity(reservationStatusEntity);
        return ResponseEntity.ok(reservationStatusResource);
    }


    @GetMapping
    @Operation(summary = "Get all reservation newStatus")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Reservation Statuses found"),
                    @ApiResponse(responseCode = "404", description = "Reservation Status not found")
            }
    )
    public ResponseEntity<List<ReservationStatusResource>> getAllReservationStatuses(){
        var reservationStatuses = reservationStatusQueryService.handle(new GetAllReservationStatusQuery());
        if (reservationStatuses.isEmpty()) return ResponseEntity.notFound().build();
        var reservationStatusResources = reservationStatuses.stream()
                .map(ReservationStatusResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(reservationStatusResources);
    }
}
