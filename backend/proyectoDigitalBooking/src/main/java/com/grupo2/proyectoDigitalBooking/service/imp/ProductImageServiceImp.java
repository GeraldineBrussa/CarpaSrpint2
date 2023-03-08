package com.grupo2.proyectoDigitalBooking.service.imp;

import com.grupo2.proyectoDigitalBooking.model.ProductImage;
import com.grupo2.proyectoDigitalBooking.repository.IProductImageRepository;
import com.grupo2.proyectoDigitalBooking.service.interfaces.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductImageServiceImp implements ProductImageService {


    private final IProductImageRepository productImageRepository;


    @Autowired
    public ProductImageServiceImp(IProductImageRepository productImageRepository) {
        this.productImageRepository = productImageRepository;
    }


    public ProductImage addProductImage(ProductImage productImage){
        return productImageRepository.save(productImage);
    }


    public Optional<ProductImage> searchProductImage(Long id){
        return productImageRepository.findById(id);
    }


    public ProductImage editProductImage(ProductImage productImage){
        return productImageRepository.save(productImage);
    }


    public void deleteProductImage(Long id) throws Exception {
        Optional<ProductImage> searchedProductFeature = searchProductImage(id);
        if (searchedProductFeature.isPresent())
            productImageRepository.deleteById(id);
        else
            throw new Exception("The relationship between product and image has not been found");
    }


    public List<ProductImage> listProductImage(){
        List<ProductImage> productImage = productImageRepository.findAll();
        return productImage;
    }


    public List<ProductImage> searchByProduct(Long idProduct){
        return productImageRepository.findByProductId(idProduct);
    }
}
