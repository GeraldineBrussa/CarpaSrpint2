package com.grupo2.proyectoDigitalBooking.service.interfaces;

import com.grupo2.proyectoDigitalBooking.model.City;
import java.util.List;
import java.util.Optional;

public interface CityService {


    City addCity(City city);

    Optional<City> searchCity(Long id);

    City editCity(City city);

    void deleteCity(Long id) throws Exception;

    List<City> listCities();
}
