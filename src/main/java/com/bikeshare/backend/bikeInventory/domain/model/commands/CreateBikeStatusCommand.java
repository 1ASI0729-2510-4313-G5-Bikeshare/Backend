package com.bikeshare.backend.bikeInventory.domain.model.commands;

public record CreateBikeStatusCommand(String statusName) {

    public CreateBikeStatusCommand {
        if (statusName.length() < 3) {
            throw new IllegalArgumentException("Status name must be at least 3 characters");
        }
    }
}
