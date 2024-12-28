package com.elearn.app.start_learn_back.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String userId;

    private String name;

    private String email;

    private String password;

    private String about;

    private boolean active;

    private String phoneNumber;

    private boolean emailVerified;

    private boolean smsVerified;

    private Date createdAt;

    private String profilePath;

    private String recentOtp;
}
