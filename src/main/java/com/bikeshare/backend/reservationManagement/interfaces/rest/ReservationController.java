package com.bikeshare.backend.reservationManagement.interfaces.rest;


import com.bikeshare.backend.reservationManagement.domain.model.queries.GetAllReservationsQuery;
import com.bikeshare.backend.reservationManagement.domain.model.queries.GetReservationByIdQuery;
import com.bikeshare.backend.reservationManagement.domain.model.queries.GetReservationsByClientEmail;
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
@RequestMapping(value = "/api/v1/rental", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Rentals", description = "Available Rentals Endpoints")
public class ReservationController {

    private final ReservationCommandService reservationCommandService;
    private final ReservationQueryService reservationQueryService;

    ReservationController(ReservationCommandService reservationCommandService, ReservationQueryService reservationQueryService) {
        this.reservationCommandService = reservationCommandService;
        this.reservationQueryService = reservationQueryService;
    }

    @PostMapping
    @Operation(summary = "Creates a rental")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Rental created"),
                    @ApiResponse(responseCode = "400", description = "Bad Request")
            }
    )
    public ResponseEntity<ReservationResource> createRental(@RequestBody CreateReservationResource resource) {
        var createdRentalCommand = CreateReservationCommandFromResourceAssembler.toCommandFromResource(resource);
        var rental = reservationCommandService.handle(createdRentalCommand);
        if(rental.isEmpty()) return ResponseEntity.badRequest().build();
        var createdRental = rental.get();
        var rentalResource = ReservationResourceFromEntityAssembler.toResourceFromEntity(createdRental);
        return new ResponseEntity<>(rentalResource, HttpStatus.CREATED);
    }

    @GetMapping("/{rentalId}")
    @Operation(summary = "Get a rental by Id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Rental Found"),
                    @ApiResponse(responseCode = "404", description = "Rental not found")
            }
    )
    public ResponseEntity<ReservationResource> getRentalById(@PathVariable Long rentalId) {
        var getRentalByIdQuery = new GetReservationByIdQuery(rentalId);
        var rental = reservationQueryService.handle(getRentalByIdQuery);
        if(rental.isEmpty()) return ResponseEntity.notFound().build();
        var rentalEntity = rental.get();
        var rentalResource = ReservationResourceFromEntityAssembler.toResourceFromEntity(rentalEntity);
        return ResponseEntity.ok(rentalResource);
    }

    @GetMapping("/{clientEmail}")
    @Operation(summary = "Get a rental by client email")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Rental found"),
                    @ApiResponse(responseCode = "404", description = "Rental not found")
            }
    )
    public ResponseEntity<List<ReservationResource>> getRentalsByClientEmail(@PathVariable String clientEmail) {
        var rentals = reservationQueryService.handle(new GetReservationsByClientEmail(clientEmail));
        if(rentals.isEmpty()) return ResponseEntity.notFound().build();
        var rentalResource = rentals.stream()
                .map(ReservationResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(rentalResource);
    }

    @GetMapping
    @Operation(summary = "Get all profiles")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Rental not found"),
                    @ApiResponse(responseCode = "404", description = "Rental not found")
            }
    )
    public ResponseEntity<List<ReservationResource>> getAllRentals() {
        var rentals = reservationQueryService.handle(new GetAllReservationsQuery());
        if(rentals.isEmpty()) return ResponseEntity.notFound().build();
        var rentalResource = rentals.stream()
                .map(ReservationResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(rentalResource);
    }

    @DeleteMapping("{rentalId}")
    @Operation(
            summary = "Delete a rental",
            description = "Deletes the rental with the specified rentalId"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "User deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "User not found")
            }
    )
    public ResponseEntity<Void> deleteUser(@PathVariable("rentalId") Long rentalId) {
        boolean deleted = reservationCommandService.deleteRental(rentalId);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.notFound().build();  // 404
        }
    }

}
