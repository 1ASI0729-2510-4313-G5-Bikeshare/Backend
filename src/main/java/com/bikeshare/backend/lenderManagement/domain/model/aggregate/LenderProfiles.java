package com.bikeshare.backend.lenderManagement.domain.model.aggregate;


import com.bikeshare.backend.lenderManagement.domain.model.commands.CreateLenderProfileCommand;
import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;
import jakarta.persistence.*;
import lombok.Getter;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.text.DecimalFormat;
import java.util.Date;

@Entity
@Getter
public class LenderProfiles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lender_id", nullable = false)
    private Users lenderId;

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

    protected LenderProfiles() {};

    public LenderProfiles(CreateLenderProfileCommand command) {
        this.lenderId =command.lenderId();
        this.bio = command.bio();
        this.totalEarnings = command.totalEarnings();
        this.rating = command.rating();
    }

}
