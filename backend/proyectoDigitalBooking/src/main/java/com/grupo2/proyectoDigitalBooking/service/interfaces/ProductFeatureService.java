package com.grupo2.proyectoDigitalBooking.service.interfaces;

import com.grupo2.proyectoDigitalBooking.model.ProductFeature;

import java.util.List;
import java.util.Optional;

public interface ProductFeatureService {

    ProductFeature addProductFeature(ProductFeature productFeature);

    Optional<ProductFeature> searchProductFeature(Long id);

    ProductFeature editProductFeature(ProductFeature productFeature);

    void deleteProductFeature(Long id) throws Exception;

    List<ProductFeature> listProductFeature();

    List<ProductFeature> searchByProduct(Long id);
}
