package com.bikeshare.backend.bikeInventory.domain.model.commands;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.BikeStatus;
import com.bikeshare.backend.bikeInventory.domain.model.aggregate.BikeTypes;
import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;

public record CreateBikesCommand(Users ownerId, String model, BikeTypes typeId, BikeStatus statusId, double latitude, double longitude) {

    public CreateBikesCommand{
        if(ownerId == null) {
            throw new IllegalArgumentException("ownerId cannot be null");
        }
        if(model == null || model.length() < 3) {
            throw new IllegalArgumentException("model cannot be null");
        }
        if(typeId == null) {
            throw new IllegalArgumentException("typeId cannot be null");
        }
        if(statusId == null) {
            throw new IllegalArgumentException("statusId cannot be null");
        }
        if(latitude < 0 || longitude < 0) {
            throw new IllegalArgumentException("latitude cannot be negative");
        }
    }
}
