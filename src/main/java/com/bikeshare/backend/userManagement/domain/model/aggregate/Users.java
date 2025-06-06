package com.bikeshare.backend.userManagement.domain.model.aggregate;

import com.bikeshare.backend.userManagement.domain.model.commands.CreateUsersCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Getter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private  String email;

    @Column(nullable = false)
    private String password_hash;

    @Column(nullable = false)
    private String full_name;

    @ManyToOne(fetch = FetchType.EAGER)//Many users can have the same role
    @JoinColumn(name = "role_id", nullable = false)
    private UserRoles role;

    @CreatedDate
    private Date created_At;

    @LastModifiedDate
    private Date updated_At;

    protected Users() {}

    public Users(CreateUsersCommand command) {
        this.email= command.email();
        this.password_hash = command.password_hash();
        this.full_name= command.full_name();
        this.role =command.role();
    }

}
