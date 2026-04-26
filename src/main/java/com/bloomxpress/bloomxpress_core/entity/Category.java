package com.bloomxpress.bloomxpress_core.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "category")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Shop> shops;

    // Getters and Setters
}