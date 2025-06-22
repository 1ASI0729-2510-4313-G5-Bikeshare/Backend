package com.bikeshare.backend.ownerManagement.domain.model.aggregate;


import com.bikeshare.backend.ownerManagement.domain.model.commands.CreateOwnerProfileCommand;
import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Getter
public class OwnerProfiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", nullable = false)
    private Users owner;

    @Column(nullable = false)
    private String bio;

    @Column(nullable = false)
    private Double totalEarnings;

    @Column(nullable = false)
    private Float rating;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    protected OwnerProfiles() {};

    public OwnerProfiles(CreateOwnerProfileCommand command) {
        this.owner =command.ownerId();
        this.bio = command.bio();
        this.totalEarnings = command.totalEarnings();
        this.rating = command.rating();
    }

}
