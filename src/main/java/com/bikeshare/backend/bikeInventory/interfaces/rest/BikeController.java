package com.bikeshare.backend.bikeInventory.interfaces.rest;

import com.bikeshare.backend.bikeInventory.domain.model.commands.DeleteBikeCommand;
import com.bikeshare.backend.bikeInventory.domain.model.queries.*;
import com.bikeshare.backend.bikeInventory.domain.model.valueobjects.OwnerId;
import com.bikeshare.backend.bikeInventory.domain.services.BikeCommandService;
import com.bikeshare.backend.bikeInventory.domain.services.BikeQueryService;
import com.bikeshare.backend.bikeInventory.domain.model.valueobjects.BikeTypes;
import com.bikeshare.backend.bikeInventory.domain.model.valueobjects.Coordinates;
import com.bikeshare.backend.bikeInventory.interfaces.rest.resources.BikeResource;
import com.bikeshare.backend.bikeInventory.interfaces.rest.resources.CreateBikeResource;
import com.bikeshare.backend.bikeInventory.interfaces.rest.resources.UpdateBikeResource;
import com.bikeshare.backend.bikeInventory.interfaces.rest.transform.BikeResourceFromEntityAssembler;
import com.bikeshare.backend.bikeInventory.interfaces.rest.transform.CreateBikeCommandFromResourceAssembler;
import com.bikeshare.backend.bikeInventory.interfaces.rest.transform.UpdateBikeCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/bikes", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Bikes", description = "Endpoints")
public class BikeController {

    private final BikeCommandService bikeCommandService;
    private final BikeQueryService bikeQueryService;

    /**
     * Constructor
     * @param bikeCommandService The {@link BikeCommandService} instance
     * @param bikeQueryService The {@link BikeQueryService} instance
     */
    BikeController(BikeCommandService bikeCommandService, BikeQueryService bikeQueryService) {
        this.bikeCommandService = bikeCommandService;
        this.bikeQueryService = bikeQueryService;
    }

    /**
     * Create a new bike
     * @param resource The {@link CreateBikeResource} instance
     * @return A {@link BikeResource} resource for the created bike, or a bad request response if the bike could not be created.
     */
    @PostMapping
    @Operation(summary = "Create a new bike")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Bike created succesfully"),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            }
    )
    public ResponseEntity<BikeResource> createBike(@RequestBody CreateBikeResource resource){
        var creteBikeCommand = CreateBikeCommandFromResourceAssembler.toCommandFromResource(resource);
        var bikeId = bikeCommandService.handle(creteBikeCommand);
        if(bikeId == null || bikeId == 0L) return ResponseEntity.badRequest().build();
        var getBikeByIdQuery = new GetBikeByIdQuery(bikeId);
        var bike = bikeQueryService.handle(getBikeByIdQuery);
        if (bike.isEmpty()) return ResponseEntity.notFound().build();
        var createdBike = bike.get();
        var bikeResource = BikeResourceFromEntityAssembler.toResourceFromEntity(createdBike);
        return new ResponseEntity<>(bikeResource, HttpStatus.CREATED);
    }

    /**
     * Get a bike by ID
     * @param bikeId The bike ID
     * @return A {@link BikeResource} resource for the bike, or a not found response if the bike could not be found.
     */
    @GetMapping("/{bikeId}")
    @Operation(summary = "Get bike by Id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Bike Found"),
                    @ApiResponse(responseCode = "404", description = "Bike not found")
            }
    )
    public ResponseEntity<BikeResource> getBikeById(@PathVariable("bikeId") Long bikeId){
        var getBikeByIdQuery = new GetBikeByIdQuery(bikeId);
        var bike = bikeQueryService.handle(getBikeByIdQuery);
        if(bike.isEmpty()) return ResponseEntity.notFound().build();
        var bikeEntity = bike.get();
        var bikeResource = BikeResourceFromEntityAssembler.toResourceFromEntity(bikeEntity);
        return ResponseEntity.ok(bikeResource);
    }

    /**
     * Get bikes by OwnerId
     * @param ownerIdAsString The owner ID
     * @return A list {@link BikeResource} resource for all bikes, or a not found response if no bike could be found.
     */
    @GetMapping("/owner/{ownerId}")
    @Operation(summary = "Get bike by OwnerId")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Bike Found"),
                    @ApiResponse(responseCode = "404", description = "Bike not found")
            }
    )
    public ResponseEntity<List<BikeResource>> getBikeByOwnerId(@PathVariable("ownerId") String ownerIdAsString){
        OwnerId ownerId = OwnerId.valueOf(ownerIdAsString);
        var getBikeByOwnerIdQuery = new GetBikesByOwnerIdQuery(ownerId);
        var bike = bikeQueryService.handle(getBikeByOwnerIdQuery);
        if(bike.isEmpty()) return ResponseEntity.notFound().build();
        var bikeResource = bike.stream()
                .map( BikeResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(bikeResource);
    }

    /**
     * Get all bikes
     * @return A list of {@link BikeResource} resources for all bikes, or a not found response if no bikes are found.
     */
    @GetMapping
    @Operation(summary = "Get all bikes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bikes Found"),
            @ApiResponse(responseCode = "404", description = "Bikes not Found")
    })
    public ResponseEntity<List<BikeResource>> getAllBikes() {
        var bikes = bikeQueryService.handle(new GetAllBikesQuery());
        if (bikes.isEmpty()) return ResponseEntity.notFound().build();
        var bikeResource = bikes.stream()
                .map(BikeResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(bikeResource);
    }

    /**
     * Retrieves a list of available bikes, optionally filtered by type and price range.
     *
     * @param type     Optional filter by {@link BikeTypes} (e.g., ELECTRIC, MOUNTAIN).
     * @param minPrice Optional filter for the minimum price of the bike.
     * @param maxPrice Optional filter for the maximum price of the bike.
     * @return A {@link ResponseEntity} containing a list of {@link BikeResource} if found,
     *         or a 404 Not Found response if no bikes match the filters.
     */
    @GetMapping("/available")
    @Operation(summary = "Get available bikes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of available bikes"),
            @ApiResponse(responseCode = "404", description = "No available bikes found for the specified filters")
    })
    public ResponseEntity<List<BikeResource>> getAvailableBikes(@Parameter(description = "Type of bicycle to filter") @RequestParam(required = false) BikeTypes type,
                                                                @Parameter(description = "Minimum price") @RequestParam(required = false) BigDecimal minPrice,
                                                                @Parameter(description = "Maximum price") @RequestParam(required = false) BigDecimal maxPrice
    ) {
        var bikes = bikeQueryService.handle(new GetAvailableBikesQuery(type, minPrice, maxPrice ));
        if (bikes.isEmpty()) return ResponseEntity.notFound().build();
        var bikeResource = bikes.stream()
                .map(BikeResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(bikeResource);
    }

    /**
     * Retrieves all available bike types supported by the system.
     *
     * @return A {@link ResponseEntity} containing a list of {@link String} values representing bike types,
     *         or a 404 Not Found response if no types are available.
     */
    @GetMapping("/types")
    @Operation(summary = "Get available bikes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of available bike types"),
            @ApiResponse(responseCode = "404", description = "No bike types found")
    })
    public ResponseEntity<List<String>> GetAllBikeTypesQuery() {
        var getAllBikeTypesQuery = new GetAllBikeTypesQuery();
        var bikeTypes = bikeQueryService.handle(getAllBikeTypesQuery);
        if (bikeTypes == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bikeTypes);
    }

    /**
     * Update bike
     *
     * @param bikeId The bike id
     * @param resource The {@link UpdateBikeResource} instance
     * @return The {@link BikeResource} resource for the updated bike
     */
    @PutMapping("/{bikeId}")
    @Operation(summary = "Get available bikes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bike Found"),
            @ApiResponse(responseCode = "404", description = "Bike not Found")
    })
    public ResponseEntity<BikeResource> updateBike(@PathVariable Long bikeId, @RequestBody UpdateBikeResource resource) {
        var updateBikeCommand = UpdateBikeCommandFromResourceAssembler.toCommandFromResource(bikeId, resource);
        var updatedBike = bikeCommandService.handle(updateBikeCommand);
        if (updatedBike.isEmpty()) return ResponseEntity.notFound().build();
        var updatedBikeEntity = updatedBike.get();
        var updatedBikeResource = BikeResourceFromEntityAssembler.toResourceFromEntity(updatedBikeEntity);
        return ResponseEntity.ok(updatedBikeResource);
    }

    /**
     * Delete bike
     *
     * @param bikeId The bike id
     * @param requesterId The requester Id
     * @return The message for the deleted course
     */
    @DeleteMapping("/{bikeId}")
    @Operation(summary = "Delete Bike", description = "Delete Bike")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bike deleted"),
            @ApiResponse(responseCode = "404", description = "Bike not found")})
    public ResponseEntity<?> deleteBike(@PathVariable Long bikeId, @Parameter(description = "Requester Id") @RequestParam Long requesterId) {
        var deleteBikeCommand = new DeleteBikeCommand(bikeId, requesterId);
        bikeCommandService.handle(deleteBikeCommand);
        return ResponseEntity.ok("Bike with given id successfully deleted");
    }
}
