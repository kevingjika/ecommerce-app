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
@Table(name = "favourites")

public class Favourites {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinTable(name = "post")
    private Post post;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinTable(name = "users")
    private Users users;
}
