package com.application.ecommerce.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime createdAt;
    private boolean deleted;

    @Override
    public String toString() {
        return "Users{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address +
                '}';
    }

    public enum Role {
        USER,
        ADMIN
    }

}
