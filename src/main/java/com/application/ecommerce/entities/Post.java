package com.application.ecommerce.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.awt.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post")

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private Status status;
    private boolean deleted;
    @OneToOne(cascade = CascadeType.MERGE)
    private Image image;
    @OneToMany(targetEntity = Users.class, cascade = CascadeType.ALL)
    private Users users;

    private String address = users.getAddress();

    public enum Status {
        PENDING,
        APPROVED
    }

}
