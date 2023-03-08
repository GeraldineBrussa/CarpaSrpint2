package com.grupo2.proyectoDigitalBooking.repository;


import com.grupo2.proyectoDigitalBooking.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
