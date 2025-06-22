package com.bikeshare.backend.bikeInventory.domain.model.commands;

public record DeleteBikeCommand(Long id,
                                Long requesterId) {
    /**
     * Command to delete a bike.
     *
     * @param id           The ID of the bike to be deleted.
     *                     Must not be null.
     * @param requesterId  The ID of the requester performing the delete operation.
     *                     Must not be null.
     */
    public DeleteBikeCommand {
        /**
         * Constructor for {@link DeleteBikeCommand}.
         *
         * @param id           The ID of the bike to be deleted.
         *                     Must not be null.
         * @param requesterId  The ID of the requester performing the delete operation.
         *                     Must not be null.
         * @throws IllegalArgumentException if either id or requesterId is null.
         */
        if (id == null) {
            throw new IllegalArgumentException("Bike Id is required");
        }
        if (requesterId == null) {
            throw new IllegalArgumentException("Requester Id is required");
        }
    }
}
