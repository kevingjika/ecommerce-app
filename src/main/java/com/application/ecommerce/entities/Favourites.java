package com.application.ecommerce.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "favourites")

public class Favourites {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(targetEntity = Post.class, cascade = CascadeType.MERGE)
    private Post post;
    @ManyToOne(targetEntity = Users.class, cascade = CascadeType.MERGE)
    private Users users;
}
