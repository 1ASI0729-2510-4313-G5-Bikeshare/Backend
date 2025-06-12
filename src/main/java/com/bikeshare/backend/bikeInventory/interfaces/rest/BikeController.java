package com.bikeshare.backend.bikeInventory.interfaces.rest;

import com.bikeshare.backend.bikeInventory.domain.model.commands.CreateBikesCommand;
import com.bikeshare.backend.bikeInventory.domain.model.queries.GetAllBikesQuery;
import com.bikeshare.backend.bikeInventory.domain.model.queries.GetBikesByIdQuery;
import com.bikeshare.backend.bikeInventory.domain.model.queries.GetBikesByOwnerIdQuery;
import com.bikeshare.backend.bikeInventory.domain.services.BikeCommandService;
import com.bikeshare.backend.bikeInventory.domain.services.BikeQueryService;
import com.bikeshare.backend.bikeInventory.interfaces.rest.resources.BikeResource;
import com.bikeshare.backend.bikeInventory.interfaces.rest.resources.CreateBikeResource;
import com.bikeshare.backend.bikeInventory.interfaces.rest.transform.BikeResourceFromEntityAssembler;
import com.bikeshare.backend.bikeInventory.interfaces.rest.transform.CreateBikeCommandFromResourceAssembler;
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

@RestController
@RequestMapping(value = "/api/v1/bikes", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Bikes", description = "Endpoints")
public class BikeController {

    private final BikeCommandService bikeCommandService;
    private final BikeQueryService bikeQueryService;

    BikeController(BikeCommandService bikeCommandService, BikeQueryService bikeQueryService) {
        this.bikeCommandService = bikeCommandService;
        this.bikeQueryService = bikeQueryService;
    }

    @PostMapping
    @Operation(summary = "Creates a bikes")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Bike created succesfully"),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            }
    )
    public ResponseEntity<BikeResource> createBike(@RequestBody CreateBikeResource resource){
        var creteBikeCommand = CreateBikeCommandFromResourceAssembler.toCommandFromResource(resource);
        var bike = bikeCommandService.handle(creteBikeCommand);
        if(bike.isEmpty()) return ResponseEntity.badRequest().build();
        var createdBike = bike.get();
        var bikeResource = BikeResourceFromEntityAssembler.toResourceFromEntity(createdBike);
        return new ResponseEntity<>(bikeResource, HttpStatus.CREATED);
    }

    @GetMapping("/{bikeId}")
    @Operation(summary = "Get bike by Id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Bike Found"),
                    @ApiResponse(responseCode = "404", description = "Bike not found")
            }
    )
    public ResponseEntity<BikeResource> getBikeById(@PathVariable("bikeId") Long bikeId){
        var getBikeByIdQuery = new GetBikesByIdQuery(bikeId);
        var bike = bikeQueryService.handle(getBikeByIdQuery);
        if(bike.isEmpty()) return ResponseEntity.notFound().build();
        var bikeEntity = bike.get();
        var bikeResource = BikeResourceFromEntityAssembler.toResourceFromEntity(bikeEntity);
        return ResponseEntity.ok(bikeResource);
    }


    @GetMapping("/{ownerId}")
    @Operation(summary = "Get bike by Id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Bike Found"),
                    @ApiResponse(responseCode = "404", description = "Bike not found")
            }
    )
    public ResponseEntity<List<BikeResource>> getBikeByOwnerId(@PathVariable("ownerId") Long ownerId){
        var getBikeByOwnerIdQuery = new GetBikesByOwnerIdQuery(ownerId);
        var bike = bikeQueryService.handle(getBikeByOwnerIdQuery);
        if(bike.isEmpty()) return ResponseEntity.notFound().build();
        var bikeResource = bike.stream()
                .map( BikeResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(bikeResource);
    }


    @GetMapping
    @Operation(summary = "Get all bikes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bike Found"),
            @ApiResponse(responseCode = "404", description = "Bike not Found")
    })
    public ResponseEntity<List<BikeResource>> getAllBikes() {
        var bikes = bikeQueryService.handle(new GetAllBikesQuery());
        if (bikes.isEmpty()) return ResponseEntity.notFound().build();
        var bikeResource = bikes.stream()
                .map(BikeResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(bikeResource);
    }

}
