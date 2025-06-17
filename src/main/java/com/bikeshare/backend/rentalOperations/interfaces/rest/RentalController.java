package com.bikeshare.backend.rentalOperations.interfaces.rest;


import com.bikeshare.backend.rentalOperations.domain.model.queries.GetAllRentalsQuery;
import com.bikeshare.backend.rentalOperations.domain.model.queries.GetRentalByIdQuery;
import com.bikeshare.backend.rentalOperations.domain.model.queries.GetRentalsByClientEmail;
import com.bikeshare.backend.rentalOperations.domain.services.RentalCommandService;
import com.bikeshare.backend.rentalOperations.domain.services.RentalQueryService;
import com.bikeshare.backend.rentalOperations.interfaces.rest.resources.CreateRentalResource;
import com.bikeshare.backend.rentalOperations.interfaces.rest.resources.RentalResource;
import com.bikeshare.backend.rentalOperations.interfaces.rest.transform.CreateRentalCommandFromResourceAssembler;
import com.bikeshare.backend.rentalOperations.interfaces.rest.transform.RentalResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/api/v1/rental", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Rentals", description = "Available Rentals Endpoints")
public class RentalController {

    private final RentalCommandService rentalCommandService;
    private final RentalQueryService rentalQueryService;

    RentalController(RentalCommandService rentalCommandService, RentalQueryService rentalQueryService) {
        this.rentalCommandService = rentalCommandService;
        this.rentalQueryService = rentalQueryService;
    }

    @PostMapping
    @Operation(summary = "Creates a rental")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Rental created"),
                    @ApiResponse(responseCode = "400", description = "Bad Request")
            }
    )
    public ResponseEntity<RentalResource> createRental(@RequestBody CreateRentalResource resource) {
        var createdRentalCommand = CreateRentalCommandFromResourceAssembler.toCommandFromResource(resource);
        var rental = rentalCommandService.handle(createdRentalCommand);
        if(rental.isEmpty()) return ResponseEntity.badRequest().build();
        var createdRental = rental.get();
        var rentalResource = RentalResourceFromEntityAssembler.toResourceFromEntity(createdRental);
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
    public ResponseEntity<RentalResource> getRentalById(@PathVariable Long rentalId) {
        var getRentalByIdQuery = new GetRentalByIdQuery(rentalId);
        var rental = rentalQueryService.handle(getRentalByIdQuery);
        if(rental.isEmpty()) return ResponseEntity.notFound().build();
        var rentalEntity = rental.get();
        var rentalResource = RentalResourceFromEntityAssembler.toResourceFromEntity(rentalEntity);
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
    public ResponseEntity<List<RentalResource>> getRentalsByClientEmail(@PathVariable String clientEmail) {
        var rentals = rentalQueryService.handle(new GetRentalsByClientEmail(clientEmail));
        if(rentals.isEmpty()) return ResponseEntity.notFound().build();
        var rentalResource = rentals.stream()
                .map(RentalResourceFromEntityAssembler::toResourceFromEntity)
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
    public ResponseEntity<List<RentalResource>> getAllRentals() {
        var rentals = rentalQueryService.handle(new GetAllRentalsQuery());
        if(rentals.isEmpty()) return ResponseEntity.notFound().build();
        var rentalResource = rentals.stream()
                .map(RentalResourceFromEntityAssembler::toResourceFromEntity)
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
        boolean deleted = rentalCommandService.deleteRental(rentalId);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.notFound().build();  // 404
        }
    }

}
