package com.example.blog.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "posts")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false,unique = true)
    private String publicId;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private String category;

    @Lob
    @Column(nullable = false)
    private String contenu;

    @Column(nullable = true)
    private LocalDate date;


    @OneToMany(cascade = CascadeType.ALL,mappedBy ="post")
    private List<Comment> comments;

    @Lob
    @Column(nullable = true)
    private String image;

}
