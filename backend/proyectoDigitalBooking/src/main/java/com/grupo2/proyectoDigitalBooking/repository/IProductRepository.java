package com.grupo2.proyectoDigitalBooking.repository;


import com.grupo2.proyectoDigitalBooking.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {


    List<Product> findByCategoryId(Long id_categories);

    List<Product> findByCityId(Long id_cities);

    @Query("SELECT p FROM Product p WHERE p.city.name = :cityName")
    List<Product>findByCityName(@Param("cityName") String cityName);

    @Query( value = "select P.* from products P where P.id_categories in :ids", nativeQuery = true )
    List<Product> findByCategoryIds(@Param("ids") List<Long> categoryIdList);


}
