package com.bikeshare.backend.reservationManagement.interfaces.rest;

import com.bikeshare.backend.reservationManagement.domain.model.queries.GetAllReservationsQuery;
import com.bikeshare.backend.reservationManagement.domain.model.queries.GetReservationByIdQuery;
import com.bikeshare.backend.reservationManagement.domain.model.queries.GetReservationsByRenterEmail;
import com.bikeshare.backend.reservationManagement.domain.services.ReservationCommandService;
import com.bikeshare.backend.reservationManagement.domain.services.ReservationQueryService;
import com.bikeshare.backend.reservationManagement.interfaces.rest.resources.CreateReservationResource;
import com.bikeshare.backend.reservationManagement.interfaces.rest.resources.ReservationResource;
import com.bikeshare.backend.reservationManagement.interfaces.rest.transform.CreateReservationCommandFromResourceAssembler;
import com.bikeshare.backend.reservationManagement.interfaces.rest.transform.ReservationResourceFromEntityAssembler;
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
@RequestMapping(value = "/api/v1/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Reservations", description = "Available Reservations Endpoints")
public class ReservationController {
    private final ReservationCommandService reservationCommandService;
    private final ReservationQueryService reservationQueryService;

    ReservationController(ReservationCommandService reservationCommandService, ReservationQueryService reservationQueryService) {
        this.reservationCommandService = reservationCommandService;
        this.reservationQueryService = reservationQueryService;
    }

    @PostMapping
    @Operation(summary = "Creates a reservation")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Reservation created"),
                    @ApiResponse(responseCode = "400", description = "Bad Request")
            }
    )
    public ResponseEntity<ReservationResource> createReservation(@RequestBody CreateReservationResource resource) {
        var createReservationCommand = CreateReservationCommandFromResourceAssembler.toCommandFromResource(resource);
        var reservation = reservationCommandService.handle(createReservationCommand);
        if(reservation.isEmpty()) return ResponseEntity.badRequest().build();
        var createdReservation = reservation.get();
        var reservationResource = ReservationResourceFromEntityAssembler.toResourceFromEntity(createdReservation);
        return new ResponseEntity<>(reservationResource, HttpStatus.CREATED);
    }

    @GetMapping("/{reservationId}")
    @Operation(summary = "Get a reservation by Id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Reservation Found"),
                    @ApiResponse(responseCode = "404", description = "Rental not found")
            }
    )
    public ResponseEntity<ReservationResource> getReservationById(@PathVariable Long reservationId) { // CAMBIO 6
        var getReservationByIdQuery = new GetReservationByIdQuery(reservationId);
        var reservation = reservationQueryService.handle(getReservationByIdQuery);
        if(reservation.isEmpty()) return ResponseEntity.notFound().build();
        var reservationEntity = reservation.get();
        var reservationResource = ReservationResourceFromEntityAssembler.toResourceFromEntity(reservationEntity);
        return ResponseEntity.ok(reservationResource);
    }

    @GetMapping("/by-renter/{renterEmail}")
    @Operation(summary = "Get reservations by renter email")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Reservations found"),
                    @ApiResponse(responseCode = "404", description = "Rental not found")
            }
    )
    public ResponseEntity<List<ReservationResource>> getReservationsByRenterEmail(@PathVariable String renterEmail) {
        var reservations = reservationQueryService.handle(new GetReservationsByRenterEmail(renterEmail));
        if(reservations.isEmpty()) return ResponseEntity.notFound().build();
        var reservationResources = reservations.stream()
                .map(ReservationResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(reservationResources);
    }

    @GetMapping
    @Operation(summary = "Get all reservations")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Reservations found"),
                    @ApiResponse(responseCode = "404", description = "Reservations not found")
            }
    )
    public ResponseEntity<List<ReservationResource>> getAllReservations() {
        var reservations = reservationQueryService.handle(new GetAllReservationsQuery());
        if(reservations.isEmpty()) return ResponseEntity.notFound().build();
        var reservationResources = reservations.stream()
                .map(ReservationResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(reservationResources);
    }


    @DeleteMapping("{reservationId}")
    @Operation(summary = "Delete a reservation", description = "Deletes the reservation with the specified ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Reservation deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Reservation not found")
            }
    )
    public ResponseEntity<Void> deleteReservation(@PathVariable("reservationId") Long reservationId) {
        boolean deleted = reservationCommandService.deleteReservation(reservationId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
