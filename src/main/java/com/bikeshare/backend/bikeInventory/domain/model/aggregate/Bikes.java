package com.bikeshare.backend.bikeInventory.domain.model.aggregate;

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
    private Long bike_id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ownerId", nullable = false, updatable = true, insertable = true)
    private Users ownerId;

    @Column(nullable = false)
    private String model;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typeId", nullable = false, updatable = true, insertable = true)
    private BikeTypes typeId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "statusId",nullable = false, updatable = true, insertable = true)
    private BikeStatus statusId;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

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
    }
}
