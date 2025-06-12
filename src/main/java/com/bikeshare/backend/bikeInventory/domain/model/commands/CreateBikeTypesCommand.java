package com.bikeshare.backend.bikeInventory.domain.model.commands;

public record CreateBikeTypesCommand(String typeName) {

    public CreateBikeTypesCommand {
        if (typeName == null || typeName.trim().isEmpty()) {
            throw new IllegalArgumentException("Type name cannot be null or empty");
        }
    }
}
