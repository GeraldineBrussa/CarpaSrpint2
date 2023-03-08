package com.grupo2.proyectoDigitalBooking.service.imp;

import com.grupo2.proyectoDigitalBooking.model.Feature;
import com.grupo2.proyectoDigitalBooking.repository.IFeatureRepository;
import com.grupo2.proyectoDigitalBooking.service.interfaces.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FeatureServiceImp implements FeatureService {


    private final IFeatureRepository featureRepository;


    @Autowired
    public FeatureServiceImp(IFeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    public Feature addFeature(Feature feature){return featureRepository.save(feature); }


    public Feature editFeature(Feature feature){
        return featureRepository.save(feature);
    }


    public Optional<Feature> searchFeature(Long id){
        return featureRepository.findById(id);
    }


    public void deleteFeature(Long id) throws Exception{
        Optional<Feature> searchedFeature = searchFeature(id);
        if (searchedFeature.isPresent())
            featureRepository.deleteById(id);
        else
            throw new Exception("The feature has not been found");
    }


    public List<Feature> listFeatures() {
        List<Feature> features = featureRepository.findAll();
        return features;
    }
}
