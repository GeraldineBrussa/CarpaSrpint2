package com.grupo2.proyectoDigitalBooking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Products")

public class Product {


    @Id
    @SequenceGenerator(name = "products_sequence", sequenceName = "products_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_sequence")
    @Column(name = "id_products",unique = true)
    private Long id;

    private String name;

    private String description;

    private Boolean availability;

    private String policies;


    @ToString.Exclude
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categories")
    private Category category;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "product", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ProductFeature> features = new ArrayList<>();

    @ToString.Exclude
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cities")
    private City city;



    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ProductImage> images = new ArrayList<>();
















}
