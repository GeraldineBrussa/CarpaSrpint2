package com.grupo2.proyectoDigitalBooking.service.interfaces;



import com.grupo2.proyectoDigitalBooking.exceptions.NotFoundException;
import com.grupo2.proyectoDigitalBooking.model.Product;
import com.grupo2.proyectoDigitalBooking.model.dto.ProductDTO;
import com.grupo2.proyectoDigitalBooking.model.dto.ProductEditDTO;
import com.grupo2.proyectoDigitalBooking.model.dto.ProductListDTO;


import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product addProduct(ProductDTO dto);

    Optional<Product> searchProduct(Long id);

    Product editProduct(ProductEditDTO dto) throws NotFoundException;

    void deleteProduct(Long id) throws Exception;

    ProductListDTO listProduct();

    ProductListDTO searchByCategory(Long id);

    ProductListDTO searchByCity(Long id);

    List<Product> randomProducts();
}
