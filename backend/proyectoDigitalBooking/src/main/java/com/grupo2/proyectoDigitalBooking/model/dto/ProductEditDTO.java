package com.grupo2.proyectoDigitalBooking.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ProductEditDTO {

    private Long id;
    private String name;
    private String description;
    private Long idCity;
    private Long idCategory;
    private List<ImageDTO> images;
    private List<Long> idsFeatures;
}
