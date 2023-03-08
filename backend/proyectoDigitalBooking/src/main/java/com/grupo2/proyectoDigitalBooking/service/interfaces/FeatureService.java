package com.grupo2.proyectoDigitalBooking.service.interfaces;

import com.grupo2.proyectoDigitalBooking.model.Feature;
import java.util.List;
import java.util.Optional;

public interface FeatureService {


    Feature addFeature(Feature feature);

    Optional<Feature> searchFeature(Long id);

    Feature editFeature(Feature feature);

    void deleteFeature(Long id) throws Exception;

    List<Feature> listFeatures();
}
