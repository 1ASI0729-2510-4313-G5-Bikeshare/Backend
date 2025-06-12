package com.bikeshare.backend.bikeInventory.domain.model.aggregate;

import com.bikeshare.backend.bikeInventory.domain.model.commands.CreateBikeTypesCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Getter
public class BikeTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeId;

    @Column(nullable = false)
    private  String typeName;

    protected BikeTypes() {};

    public BikeTypes(CreateBikeTypesCommand command) {
        this.typeName = command.typeName();
    }

}
