package com.grupo2.proyectoDigitalBooking.service.imp;

import com.grupo2.proyectoDigitalBooking.model.City;
import com.grupo2.proyectoDigitalBooking.repository.ICityRepository;
import com.grupo2.proyectoDigitalBooking.service.interfaces.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImp implements CityService {


    private final ICityRepository cityRepository;
    @Autowired
    public CityServiceImp(ICityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    public City addCity(City city){
        return cityRepository.save(city);
    }

    @Override
    public Optional<City> searchCity(Long id) {
        return cityRepository.findById(id);
    }

    public Optional<City> searchCityById(Long id){
        return cityRepository.findById(id);
    }

    public void deleteCity (Long id)throws Exception{
        Optional<City> searchedCity = searchCityById(id);
        if (searchedCity.isPresent())
            cityRepository.deleteById(id);
        else
            throw new Exception("The city has not been found ");
    }

    public City editCity(City city){
        return cityRepository.save(city);
    }

    public List<City> listCities(){
        List<City> cities= cityRepository.findAll();
        return cities;
    }



}

