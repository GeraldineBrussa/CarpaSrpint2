package com.grupo2.proyectoDigitalBooking.controller;

import com.grupo2.proyectoDigitalBooking.handler.ResponseHandler;
import com.grupo2.proyectoDigitalBooking.model.ProductFeature;
import com.grupo2.proyectoDigitalBooking.service.interfaces.ProductFeatureService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ProductsFeatures")
public class ProductFeaturesController {


    @Autowired
    private ProductFeatureService productFeatureService;


    @Operation(summary = "Listar todas las relaciones producto-caracteristica")
    @GetMapping("/bringAll")
    public ResponseEntity<Object> searchAllProductFeature(){
        return ResponseHandler.generateResponse("List of all product-feature relationships", HttpStatus.OK, productFeatureService.listProductFeature());
    }


    @Operation(summary = "Agregar  relacion producto-caracteristica")
    @PostMapping("/addProductFeature")
    public ResponseEntity<Object> addProductFeature(@RequestBody ProductFeature productFeature){
        return ResponseHandler.generateResponse("The relationship between product and features has been added", HttpStatus.OK, productFeatureService.addProductFeature(productFeature));
    }


    @Operation(summary = "Buscar relación producto-caracteristica por ID")
    @GetMapping("/searchProductFeatureById/{id}")
    public ResponseEntity<Object> searchProductFeature(@PathVariable Long id){
        ResponseEntity<Object> response=null;

        if (id != null && productFeatureService.searchProductFeature(id).isPresent())
            response = ResponseHandler.generateResponse("The relationship between product and features has been found", HttpStatus.OK, productFeatureService.searchProductFeature(id));
        else
            response = ResponseHandler.generateResponse("The relationship between product and features has not been found",HttpStatus.NOT_FOUND,null);

        return response;
    }


    @Operation(summary = "Editar relación producto-caracteristica")
    @PutMapping("/editProductFeature")
    public ResponseEntity<Object> editProductFeature(@RequestBody ProductFeature productFeature){
        ResponseEntity<Object> response=null;

        if (productFeature.getId() != null && productFeatureService.searchProductFeature(productFeature.getId()).isPresent())
            response = ResponseHandler.generateResponse("The product_feature relationship has been edited", HttpStatus.OK, productFeatureService.editProductFeature(productFeature));
        else
            response = ResponseHandler.generateResponse("The product_feature relationship has not been found",HttpStatus.NOT_FOUND,null);

        return response;
    }


    @Operation(summary = "Eliminar relacion producto-caracteristica")
    @DeleteMapping("/deleteProductFeature/{id}")
    public ResponseEntity<Object> deleteProductFeature(@PathVariable Long id) throws Exception {
        ResponseEntity<Object> response = null;

        if (productFeatureService.searchProductFeature(id).isPresent()) {

            productFeatureService.deleteProductFeature(id);
            response = ResponseHandler.generateResponse("The relationship between product and features has been deleted", HttpStatus.OK, null);

        }else {
            response = ResponseHandler.generateResponse("The relationship between product and features has not been found", HttpStatus.NOT_FOUND, null);
        }
        return response;
    }


    @Operation(summary = "Listar caracteristicas por producto")
    @GetMapping("/listFeatureByProduct/{id}")
    public ResponseEntity<Object> listFeatByProduct(@PathVariable Long id) throws Exception{
        ResponseEntity<Object> response = null;
        if (productFeatureService.searchByProduct(id).isEmpty()){
            response = ResponseHandler.generateResponse("\n" + "The product has not been associated with features", HttpStatus.NOT_FOUND, null);
        }else {
            response=ResponseHandler.generateResponse("Features list", HttpStatus.OK, productFeatureService.searchByProduct(id));
        }
        return response;
    }
}
