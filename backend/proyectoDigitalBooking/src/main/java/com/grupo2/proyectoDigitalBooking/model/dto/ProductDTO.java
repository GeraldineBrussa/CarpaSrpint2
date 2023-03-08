package com.grupo2.proyectoDigitalBooking.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ProductDTO {



        private String name;
        private String description;
        private Long idCity;
        private Long idCategory;
        private List<String> images;
        private List<Long> idsFeatures;

    }
