package com.grupo2.proyectoDigitalBooking.controller;

import com.grupo2.proyectoDigitalBooking.handler.ResponseHandler;
import com.grupo2.proyectoDigitalBooking.model.Image;
import com.grupo2.proyectoDigitalBooking.service.interfaces.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/Images")
public class ImageController {


    @Autowired
    private ImageService imageService;

    @Operation(summary = "Resgistrar nueva imagen")
    @PostMapping("/addImage")
    public ResponseEntity<Object> addImage(@RequestBody Image image){
        return ResponseHandler.generateResponse("The image has been added", HttpStatus.OK,imageService.addImage(image));
    }


    @Operation(summary = "Buscar imagen por ID")
    @GetMapping("/searchImage/{id}")
    public ResponseEntity<Object> searchImage(@PathVariable Long id){ResponseEntity<Object> response=null;

        if (id != null && imageService.searchImage(id).isPresent())
            response = ResponseHandler.generateResponse("The image has been found", HttpStatus.OK, imageService.searchImage(id));
        else
            response = ResponseHandler.generateResponse("The image has not been found",HttpStatus.NOT_FOUND,null);

        return response;
    }


    @Operation(summary = "Editar imagen")
    @PutMapping("/editImage")
    public ResponseEntity<Object> editImage(@RequestBody Image image){
        ResponseEntity<Object> response=null;

        if (image.getId() != null && imageService.searchImage(image.getId()).isPresent())
            response = ResponseHandler.generateResponse("The image has been edited", HttpStatus.OK, imageService.editImage(image));
        else
            response = ResponseHandler.generateResponse("The image has not been found",HttpStatus.NOT_FOUND,null);

        return response;
    }

    @Operation(summary = "Eliminar imagen")
    @DeleteMapping("/deleteImage/{id}")
    public ResponseEntity<Object> deleteImage(@PathVariable Long id) throws Exception {
        ResponseEntity<Object> response = null;

        if (imageService.searchImage(id).isPresent()) {
            imageService.deleteImage(id);
            response = ResponseHandler.generateResponse("The feature has been deleted", HttpStatus.OK, imageService.searchImage(id));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
            return response;
    }

    @Operation(summary = "Listar todas las imagenes")
    @GetMapping("/listImages")
    public ResponseEntity<Object> listImages(){
        return ResponseHandler.generateResponse("List of all Images", HttpStatus.OK, imageService.listImages());
    }
}
