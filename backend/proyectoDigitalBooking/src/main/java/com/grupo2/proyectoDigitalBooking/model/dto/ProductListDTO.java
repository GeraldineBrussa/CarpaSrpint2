package com.grupo2.proyectoDigitalBooking.model.dto;

import com.grupo2.proyectoDigitalBooking.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class ProductListDTO {

    private List<Product> items;
    private long total;
}
