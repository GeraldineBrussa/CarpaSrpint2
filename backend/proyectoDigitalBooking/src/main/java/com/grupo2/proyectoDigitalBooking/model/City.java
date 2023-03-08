package com.grupo2.proyectoDigitalBooking.model;


import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Cities")

public class City {
    @Id
    @SequenceGenerator(name = "cities_sequence", sequenceName = "cities_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cities_sequence")
    @Column(name = "id_cities",unique = true)
    private Long id;

    private String name;

    private String province;

    private String country;




}
