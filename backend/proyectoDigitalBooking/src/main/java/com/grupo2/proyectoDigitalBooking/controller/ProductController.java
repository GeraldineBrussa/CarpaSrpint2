package com.grupo2.proyectoDigitalBooking.controller;

import com.grupo2.proyectoDigitalBooking.exceptions.NotFoundException;
import com.grupo2.proyectoDigitalBooking.handler.ResponseHandler;
import com.grupo2.proyectoDigitalBooking.model.dto.ProductDTO;
import com.grupo2.proyectoDigitalBooking.model.dto.ProductEditDTO;
import com.grupo2.proyectoDigitalBooking.service.interfaces.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/Products")
public class ProductController {


    @Autowired
    private ProductService productService;


    @Operation(summary = "Registrar nuevo producto")
    @PostMapping("/addProduct")
    public ResponseEntity<Object> addProduct(@RequestBody ProductDTO productDTO){
        return ResponseHandler.generateResponse("The product has been added", HttpStatus.OK, productService.addProduct(productDTO));
    }

    @Operation(summary = "Listar todos los productos")
    @GetMapping("/bringAll")
    public ResponseEntity<Object> searchAllProducts(){
        return ResponseHandler.generateResponse("List of all products", HttpStatus.OK, productService.listProduct());
    }

    @Operation(summary = "Buscar producto por ID")
    @GetMapping("/searchProductById/{id}")
    public ResponseEntity<Object> searchProduct(@PathVariable Long id){
        ResponseEntity<Object> response=null;

        if (id != null && productService.searchProduct(id).isPresent())
            response = ResponseHandler.generateResponse("The product has been found", HttpStatus.OK, productService.searchProduct(id));
        else
            response = ResponseHandler.generateResponse("The product has not been found",HttpStatus.NOT_FOUND,null);

        return response;
    }

    @Operation(summary = "Editar producto")
    @PutMapping("/editProduct")
    public ResponseEntity<Object> editProduct(@RequestBody ProductEditDTO productDTO){
        try{
            return ResponseHandler.generateResponse("The product has been edited", HttpStatus.OK, productService.editProduct(productDTO));
        }catch(NotFoundException ex){
            return ResponseHandler.generateResponse("The product has not been found",HttpStatus.NOT_FOUND,ex.getMessage());
        }catch(Exception ex){
            ex.printStackTrace();
            return ResponseHandler.generateResponse("Error to edit the product",HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage());
        }
    }

    @Operation(summary = "Eliminar producto")
    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id) throws Exception {
        ResponseEntity<Object> response = null;

        if (productService.searchProduct(id).isPresent()) {
            productService.deleteProduct(id);
            response = ResponseHandler.generateResponse("The product has been deleted", HttpStatus.OK, null);

        }else {
            response = ResponseHandler.generateResponse("The product has not been found", HttpStatus.NOT_FOUND, null);
        }
        return response;
    }

    @Operation(summary = "Buscar producto por ciudad")
    @GetMapping("/city/{id}")
    public ResponseEntity<Object> searchByCity(@PathVariable Long id){
        return ResponseHandler.generateResponse("\n" +"List of products of the selected city",HttpStatus.OK,productService.searchByCity(id));
    }

    @Operation(summary = "Buscar producto por categoría")
    @GetMapping("/category/{id}")
    public ResponseEntity<Object> searchByCategory(@PathVariable Long id){
        return ResponseHandler.generateResponse("\n" +"List of products of the selected category",HttpStatus.OK,productService.searchByCategory(id));
    }

    @Operation(summary = "Listar productos aleatoriamente")
    @GetMapping("/random")
    public ResponseEntity<Object> randomProducts(){
        return ResponseHandler.generateResponse("Random list of products", HttpStatus.OK, productService.randomProducts());
    }
}
