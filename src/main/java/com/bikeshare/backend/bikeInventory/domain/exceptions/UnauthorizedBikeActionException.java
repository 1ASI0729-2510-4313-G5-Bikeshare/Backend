package com.bikeshare.backend.bikeInventory.domain.exceptions;

public class UnauthorizedBikeActionException extends RuntimeException {
    public UnauthorizedBikeActionException(Long requesterId) {
        super(String.format("The requester with ID %s is not the owner of the bicycle", requesterId));
    }
}
