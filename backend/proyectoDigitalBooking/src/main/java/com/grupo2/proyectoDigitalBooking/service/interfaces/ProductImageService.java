package com.grupo2.proyectoDigitalBooking.service.interfaces;

import com.grupo2.proyectoDigitalBooking.model.ProductImage;

import java.util.List;
import java.util.Optional;

public interface ProductImageService {


    ProductImage addProductImage(ProductImage productImage);

    Optional<ProductImage> searchProductImage(Long id);

    ProductImage editProductImage(ProductImage productImage);

    void deleteProductImage(Long id) throws Exception;

    List<ProductImage> listProductImage();

    List<ProductImage> searchByProduct(Long idProduct);
}
