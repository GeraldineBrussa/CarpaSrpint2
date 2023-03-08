package com.grupo2.proyectoDigitalBooking.repository;

import com.grupo2.proyectoDigitalBooking.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductImageRepository extends JpaRepository<ProductImage, Long> {
    List<ProductImage> findByProductId(Long id_products);

}
