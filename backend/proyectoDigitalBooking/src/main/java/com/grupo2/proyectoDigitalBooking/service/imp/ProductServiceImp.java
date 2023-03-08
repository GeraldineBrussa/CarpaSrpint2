package com.grupo2.proyectoDigitalBooking.service.imp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo2.proyectoDigitalBooking.exceptions.NotFoundException;
import com.grupo2.proyectoDigitalBooking.model.*;
import com.grupo2.proyectoDigitalBooking.model.dto.*;
import com.grupo2.proyectoDigitalBooking.repository.*;
import com.grupo2.proyectoDigitalBooking.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {

    private final IProductRepository productRepository;
    private final ICityRepository cityRepository;
    private final ICategoryRepository categoryRepository;
    private final IFeatureRepository featureRepository;

    private ObjectMapper mapper;

    private final Integer LIMIT_RANDOM = 3;

    @Autowired
    public ProductServiceImp(IProductRepository productRepository,
                              ICityRepository cityRepository,
                              ICategoryRepository categoryRepository,
                              IFeatureRepository featureRepository,
                              ObjectMapper mapper) {
        this.productRepository = productRepository;
        this.cityRepository = cityRepository;
        this.categoryRepository = categoryRepository;
        this.featureRepository = featureRepository;
        this.mapper = mapper;
    }

    @Transactional(rollbackFor = Exception.class)
    public Product addProduct(ProductDTO dto){

        City city =  cityRepository
                .findById(dto.getIdCity())
                .orElseThrow(() -> new RuntimeException("The city has not been found"));

        Category category =  categoryRepository
                .findById(dto.getIdCategory())
                .orElseThrow(() -> new RuntimeException("The category has not been found"));

        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setCity(city);
        product.setCategory(category);

        List<ProductImage> productImages = dto
                .getImages()
                .stream()
                .map(url -> {
                    Image img = new Image();
                    img.setTitle(product.getName());
                    img.setUrl(url);
                    return img;
                }).map(image -> {
                    ProductImage productImage = new ProductImage();
                    productImage.setProduct(product);
                    productImage.setImage(image);
                    return productImage;
                }).collect(Collectors.toList());

        List<ProductFeature> productFeatures = featureRepository
                .findByIdsFeatures(dto.getIdsFeatures())
                .stream()
                .map(feature -> {
                    ProductFeature productFeature = new ProductFeature();
                    productFeature.setProduct(product);
                    productFeature.setFeature(feature);
                    return productFeature;
                }).collect(Collectors.toList());

        product.getImages().addAll(productImages);
        product.getFeatures().addAll(productFeatures);

        return productRepository.save(product);
    }

    public Product editProduct(ProductEditDTO dto){
        Product  product = searchProduct(dto.getId())
                .orElseThrow(() -> new NotFoundException(dto.getId()));

        City city =  cityRepository
                .findById(dto.getIdCity())
                .orElseThrow(() -> new RuntimeException("The city has not been found"));

        Category category =  categoryRepository
                .findById(dto.getIdCategory())
                .orElseThrow(() -> new RuntimeException("The category  has not been found"));

        List<ProductImage> productImages = dto
                .getImages()
                .stream()
                .map(img ->
                        product.getImages()
                                .stream()
                                .filter(x -> x.getImage().getId().equals(img.getId()))
                                .collect(Collectors.toList())
                                .stream()
                                .findFirst()
                                .map(x -> {
                                    x.getImage().setTitle(img.getTitle());
                                    x.getImage().setUrl(img.getUrl());
                                    return x;
                                })
                                .orElse(new ProductImage(null, product, new Image(img.getId(), img.getTitle(), img.getUrl()))))
                .collect(Collectors.toList());

        List<ProductFeature> productFeatures = featureRepository
                .findByIdsFeatures(dto.getIdsFeatures())
                .stream()
                .map(feature -> product.getFeatures()
                        .stream()
                        .filter(featureProduct ->
                                Objects.equals(
                                        featureProduct.getFeature().getId(), feature.getId())
                        ).collect(Collectors.toList()).stream().findFirst()
                        .orElse(new ProductFeature(null, product, feature))).collect(Collectors.toList());

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setCity(city);
        product.setCategory(category);
        product.getImages().clear();
        product.getFeatures().clear();
        product.getImages().addAll(productImages);
        product.getFeatures().addAll(productFeatures);

        return productRepository.save(product);
    }


    public Optional<Product> searchProduct(Long id){
        return productRepository.findById(id);
    }

    public ProductListDTO listProduct(){
        List<Product> items = productRepository.findAll();
        ProductListDTO response = new ProductListDTO();
        response.setItems(items);
        response.setTotal(items.size());

        return response;
    }

    public void deleteProduct(Long id) throws Exception {
        Optional<Product> searchedProduct = searchProduct(id);
        if (searchedProduct.isPresent())
            productRepository.deleteById(id);
        else
            throw new Exception("The product with id: "+id+" has not been found");

    }

    public ProductListDTO searchByCategory(Long id){
        List<Product> items = productRepository.findByCategoryId(id);
        ProductListDTO response = new ProductListDTO();
        response.setItems(items);
        response.setTotal(items.size());

        return response;
    }

    public ProductListDTO searchByCity(Long id){
        List<Product> items = productRepository.findByCityId(id);
        ProductListDTO response = new ProductListDTO();
        response.setItems(items);
        response.setTotal(items.size());

        return response;
    }

    @Override
    public List<Product> randomProducts() {
        List<Category> categories = categoryRepository.findAll();
        Collections.shuffle(categories);
        Integer indexEnd = categories.size() < LIMIT_RANDOM ? categories.size()-1 : LIMIT_RANDOM -1;
        List<Category> categoriesSubList = categories.subList(0, indexEnd);
        if (categories.isEmpty()){
            return Collections.EMPTY_LIST;
        }
        List<Product> products = productRepository
                .findByCategoryIds(
                        categoriesSubList
                                .stream()
                                .map(Category::getId)
                                .collect(Collectors.toList())
                );
        Collections.shuffle(products);
        return products.subList(0, getRandomNumber(0, products.size() -1));//Map: toma un objeto, lo convierte y devuelve siempre la misma cantidad. Transforma la lista de categorías en una lista de enteros (misma cantidad).
    }

    public int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }


}
