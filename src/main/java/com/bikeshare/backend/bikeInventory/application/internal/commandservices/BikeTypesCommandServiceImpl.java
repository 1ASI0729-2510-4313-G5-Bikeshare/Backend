package com.bikeshare.backend.bikeInventory.application.internal.commandservices;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.BikeTypes;
import com.bikeshare.backend.bikeInventory.domain.model.commands.CreateBikeTypesCommand;
import com.bikeshare.backend.bikeInventory.domain.services.BikeTypesCommandService;
import com.bikeshare.backend.bikeInventory.infrastructure.persistence.jpa.BikeTypesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BikeTypesCommandServiceImpl implements BikeTypesCommandService {

    private final BikeTypesRepository bikeTypesRepository;

    BikeTypesCommandServiceImpl(BikeTypesRepository bikeTypesRepository) {
        this.bikeTypesRepository = bikeTypesRepository;
    }

    @Override
    public Optional<BikeTypes> handle(CreateBikeTypesCommand command) {
        if(bikeTypesRepository.existsByTypeName(command.typeName())){
            throw new IllegalArgumentException("Type name already exists");
        }

        var bikeTypes = new BikeTypes(command);
        var createdBikeTypes = bikeTypesRepository.save(bikeTypes);

        return Optional.of(createdBikeTypes);
    }
}
