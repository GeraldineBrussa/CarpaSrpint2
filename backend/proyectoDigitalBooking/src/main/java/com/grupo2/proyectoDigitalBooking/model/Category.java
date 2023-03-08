package com.grupo2.proyectoDigitalBooking.model;

import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Categories")
public class Category{

    @Id
    @SequenceGenerator(name = "categories_sequence", sequenceName = "categories_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator = "categories_sequence")
    @Column(name = "id_categories")
    private Long id;

    private String title;

    private String description;


    @Column(name= "image_url")
    private String image;

}
