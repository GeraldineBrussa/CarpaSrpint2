package com.grupo2.proyectoDigitalBooking.repository;


import com.grupo2.proyectoDigitalBooking.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IImageRepository extends JpaRepository<Image, Long> {


}
