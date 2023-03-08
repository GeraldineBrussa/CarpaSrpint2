package com.grupo2.proyectoDigitalBooking.model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Images")

public class Image {

    @Id
    @SequenceGenerator(name = "images_sequence", sequenceName = "images_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "images_sequence")

    @Column(name = "id_images",unique = true)
    private Long id;

    private String title;

    private String url;



}
