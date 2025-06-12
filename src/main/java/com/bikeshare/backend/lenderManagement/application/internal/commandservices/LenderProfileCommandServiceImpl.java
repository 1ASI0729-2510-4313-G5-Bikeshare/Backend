package com.bikeshare.backend.lenderManagement.application.internal.commandservices;

import com.bikeshare.backend.lenderManagement.domain.model.aggregate.LenderProfiles;
import com.bikeshare.backend.lenderManagement.domain.model.commands.CreateLenderProfileCommand;
import com.bikeshare.backend.lenderManagement.domain.services.LenderProfileCommandService;
import com.bikeshare.backend.lenderManagement.infrastructure.persistence.jpa.LenderProfilesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LenderProfileCommandServiceImpl implements LenderProfileCommandService {

    private final LenderProfilesRepository lenderProfilesRepository;

    LenderProfileCommandServiceImpl(LenderProfilesRepository lenderProfilesRepository) {
        this.lenderProfilesRepository = lenderProfilesRepository;
    }

    @Override
    public Optional<LenderProfiles> handle(CreateLenderProfileCommand command) {
        var lenderProfile = new LenderProfiles(command);
        if(lenderProfilesRepository.existsByLenderId_UserId(command.lenderId().getUserId())){
            throw new IllegalArgumentException("Lender profile already exists");
        }
        lenderProfilesRepository.save(lenderProfile);

        return Optional.of(lenderProfile);

    }
}
