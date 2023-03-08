package com.grupo2.proyectoDigitalBooking.model;


import jakarta.persistence.*;
import lombok.*;




@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Features")

public class Feature {

    @Id
    @SequenceGenerator(name = "features_sequence", sequenceName = "features_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "features_sequence")
    @Column(unique = true, nullable = false)
    private Long id;

    private String name;

    private String icon;

}
