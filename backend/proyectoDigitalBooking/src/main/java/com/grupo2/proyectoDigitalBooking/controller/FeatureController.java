package com.grupo2.proyectoDigitalBooking.controller;


import com.grupo2.proyectoDigitalBooking.handler.ResponseHandler;
import com.grupo2.proyectoDigitalBooking.model.Feature;
import com.grupo2.proyectoDigitalBooking.service.interfaces.FeatureService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Features")
public class FeatureController {


    @Autowired
    private FeatureService featureService;

    @Operation(summary = "Registrar nueva caracteristica")
    @PostMapping("/addFeature")
    public ResponseEntity<Object> addFeature(@RequestBody Feature feature){
        return ResponseHandler.generateResponse("The feature has been added", HttpStatus.OK,featureService.addFeature(feature));
    }

    @Operation(summary = "Buscar caracteristica por ID")
    @GetMapping("/searchFeature/{id}")
    public ResponseEntity<Object> searchFeature(@PathVariable Long id){
        ResponseEntity<Object> response=null;

        if (id != null && featureService.searchFeature(id).isPresent())
            response = ResponseHandler.generateResponse("The feature has been found", HttpStatus.OK, featureService.searchFeature(id));
        else
            response = ResponseHandler.generateResponse("The feature has not been found",HttpStatus.NOT_FOUND,null);

        return response;

    }

    @Operation(summary = "Eliminar caracteristica")
    @DeleteMapping("/deleteFeature/{id}")
    public ResponseEntity<Object> deleteFeature(@PathVariable Long id) throws Exception {
        ResponseEntity<Object> response = null;

        if (featureService.searchFeature(id).isPresent()) {

            featureService.deleteFeature(id);
            response = ResponseHandler.generateResponse("The feature has been deleted", HttpStatus.OK, null);

        } else {
            response = ResponseHandler.generateResponse("The feature has not been deleted", HttpStatus.NOT_FOUND, null);
        }
        return response;
    }
    
    @Operation(summary = "Editar caracteristica")
    @PutMapping("/editFeature")
    public ResponseEntity<Object> editFeature(@RequestBody Feature feature){
        ResponseEntity<Object> response=null;

        if (feature.getId() != null && featureService.searchFeature(feature.getId()).isPresent())
            response = ResponseHandler.generateResponse("The feature has been edited", HttpStatus.OK, featureService.editFeature(feature));
        else
            response = ResponseHandler.generateResponse("The feature has not been found",HttpStatus.NOT_FOUND,null);

        return response;
    }

    @Operation(summary = "Listar todas las caracteristicas")
    @GetMapping("/listFeatures")
    public ResponseEntity<Object> listFeatures(){
        return ResponseHandler.generateResponse("List of all features", HttpStatus.OK, featureService.listFeatures());
    }
}
