package com.bikeshare.backend.bikeInventory.domain.model.aggregate;

import com.bikeshare.backend.bikeInventory.domain.model.commands.CreateBikesCommand;
import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Getter
public class Bikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bikeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", nullable = false, updatable = true, insertable = true)
    private Users ownerId;

    @Column(nullable = false)
    private String model;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id", nullable = false, updatable = true, insertable = true)
    private BikeTypes typeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id",nullable = false, updatable = true, insertable = true)
    private BikeStatus statusId;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private Double costPerMinute;

    @CreatedDate
    private Date created_at;

    @LastModifiedDate
    private Date updated_at;

    protected Bikes() {};

    public Bikes(CreateBikesCommand command){
        this.ownerId = command.ownerId();
        this.model = command.model();
        this.typeId = command.typeId();
        this.statusId = command.statusId();
        this.latitude = command.latitude();
        this.longitude = command.longitude();
        this.imageUrl = command.imageUrl();
        this.costPerMinute = command.costPerMinute();
    }
}
