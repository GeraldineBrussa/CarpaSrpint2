package com.grupo2.proyectoDigitalBooking.controller;



import com.grupo2.proyectoDigitalBooking.handler.ResponseHandler;
import com.grupo2.proyectoDigitalBooking.model.City;
import com.grupo2.proyectoDigitalBooking.service.interfaces.CityService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/Cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @Operation(summary = "Registrar nueva ciudad")
    @PostMapping("/addCity")
    public ResponseEntity<Object> addCity(@RequestBody City city) {
        return ResponseHandler.generateResponse("The city has been added", HttpStatus.OK,cityService.addCity(city));
    }

    @Operation(summary = "Buscar ciudad por ID")
    @GetMapping("/searchCity/{id}")
    public ResponseEntity<Object> searchCityById(@PathVariable Long id) {
        ResponseEntity<Object> response=null;

        if (id != null && cityService.searchCity(id).isPresent())
            response = ResponseHandler.generateResponse("The city has been found", HttpStatus.OK, cityService.searchCity(id));
        else
            response = ResponseHandler.generateResponse("The city has been not found",HttpStatus.NOT_FOUND,null);

        return response;
    }

    @Operation(summary = "Editar ciudad")
    @PutMapping("/editCity")
    public ResponseEntity<Object> editCity(@RequestBody City city) {
        ResponseEntity<Object> response=null;

        if (city.getId() != null && cityService.searchCity(city.getId()).isPresent())
            response = ResponseHandler.generateResponse("The city has been edited", HttpStatus.OK, cityService.editCity(city));
        else
            response = ResponseHandler.generateResponse("The city has been not found",HttpStatus.NOT_FOUND,null);

        return response;
    }

    @Operation(summary = "Eliminar ciudad")
    @DeleteMapping("/deleteCity/{id}")
    public ResponseEntity<Object> deleteCity(@PathVariable Long id) throws Exception {
        ResponseEntity<Object> response = null;

        if (cityService.searchCity(id).isPresent()) {

            cityService.deleteCity(id);
            response = ResponseHandler.generateResponse("The city has been deleted", HttpStatus.OK, null);

        }else {
            response = ResponseHandler.generateResponse("The city has been not found", HttpStatus.NOT_FOUND, null);
        }
        return response;
    }

    @Operation(summary = "Listar todas las ciudades")
    @GetMapping("/listCities")
    public ResponseEntity<Object> listCities() {
        return ResponseHandler.generateResponse("This is the list of cities", HttpStatus.OK, cityService.listCities());
    }
}
