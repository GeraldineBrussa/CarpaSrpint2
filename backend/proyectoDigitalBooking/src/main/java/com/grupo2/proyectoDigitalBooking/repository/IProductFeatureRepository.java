package com.grupo2.proyectoDigitalBooking.repository;

import com.grupo2.proyectoDigitalBooking.model.ProductFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IProductFeatureRepository extends JpaRepository<ProductFeature, Long> {

    List<ProductFeature> findByProductId(Long id_products);
}
