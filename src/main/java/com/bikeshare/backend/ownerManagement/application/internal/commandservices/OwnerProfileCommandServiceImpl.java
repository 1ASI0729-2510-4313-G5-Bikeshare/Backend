package com.bikeshare.backend.ownerManagement.application.internal.commandservices;

import com.bikeshare.backend.ownerManagement.domain.model.aggregate.OwnerProfiles;
import com.bikeshare.backend.ownerManagement.domain.model.commands.CreateOwnerProfileCommand;
import com.bikeshare.backend.ownerManagement.domain.services.OwnerProfileCommandService;
import com.bikeshare.backend.ownerManagement.infrastructure.persistence.jpa.OwnerProfilesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerProfileCommandServiceImpl implements OwnerProfileCommandService {

    private final OwnerProfilesRepository ownerProfilesRepository;

    OwnerProfileCommandServiceImpl(OwnerProfilesRepository ownerProfilesRepository) {
        this.ownerProfilesRepository = ownerProfilesRepository;
    }

    @Override
    public Optional<OwnerProfiles> handle(CreateOwnerProfileCommand command) {
        var lenderProfile = new OwnerProfiles(command);
        if(ownerProfilesRepository.existsByLenderId_UserId(command.lenderId().getUserId())){
            throw new IllegalArgumentException("Lender profile already exists");
        }
        ownerProfilesRepository.save(lenderProfile);

        return Optional.of(lenderProfile);

    }
}
