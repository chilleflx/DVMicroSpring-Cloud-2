package com.mproduits.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titre;

    private String description;

    private String image;

    private Double prix;


}
