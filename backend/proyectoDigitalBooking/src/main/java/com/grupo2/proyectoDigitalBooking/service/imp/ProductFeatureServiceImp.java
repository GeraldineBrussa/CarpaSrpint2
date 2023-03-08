package com.grupo2.proyectoDigitalBooking.service.imp;

import com.grupo2.proyectoDigitalBooking.model.Feature;
import com.grupo2.proyectoDigitalBooking.model.Product;
import com.grupo2.proyectoDigitalBooking.model.ProductFeature;
import com.grupo2.proyectoDigitalBooking.repository.IFeatureRepository;
import com.grupo2.proyectoDigitalBooking.repository.IProductRepository;
import com.grupo2.proyectoDigitalBooking.repository.IProductFeatureRepository;
import com.grupo2.proyectoDigitalBooking.service.interfaces.ProductFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class ProductFeatureServiceImp implements ProductFeatureService {

    private final IProductFeatureRepository productFeatureRepository;
    private final IProductRepository productRepository;
    private final IFeatureRepository featureRepository;

    @Autowired
    public ProductFeatureServiceImp(IProductFeatureRepository productFeatureRepository, IProductRepository productRepository, IFeatureRepository featureRepository) {
        this.productFeatureRepository = productFeatureRepository;
        this.featureRepository= featureRepository;
        this.productRepository= productRepository;
    }

    public ProductFeature addProductFeature(ProductFeature productFeature){

        Optional<Product> product = productRepository.findById(productFeature.getProduct().getId());
        productFeature.setProduct(product.get());
        Optional<Feature> feature = featureRepository.findById(productFeature.getFeature().getId());
        productFeature.setFeature(feature.get());

        return productFeatureRepository.save(productFeature);
    }

    public Optional<ProductFeature> searchProductFeature(Long id){

        return productFeatureRepository.findById(id);
    }

    public ProductFeature editProductFeature(ProductFeature productFeature){
        Optional<Product> product =  productRepository.findById(productFeature.getProduct().getId());
        productFeature.setProduct(product.get());
        Optional<Feature> feature =  featureRepository.findById(productFeature.getFeature().getId());
        productFeature.setFeature(feature.get());

        return productFeatureRepository.save(productFeature);
    }

    public void deleteProductFeature(Long id) throws Exception {
        Optional<ProductFeature> searchedProductFeature = searchProductFeature(id);
        if (searchedProductFeature.isPresent())
            productFeatureRepository.deleteById(id);
        else
            throw new Exception("The relationship between product and feature has not been found");
    }

    public List<ProductFeature> listProductFeature(){

        List<ProductFeature> productFeature = productFeatureRepository.findAll();

        return productFeature;
    }

    public List<ProductFeature> searchByProduct(Long id){

        return productFeatureRepository.findByProductId(id);
    }


}
