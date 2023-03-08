package com.grupo2.proyectoDigitalBooking.controller;


import com.grupo2.proyectoDigitalBooking.handler.ResponseHandler;
import com.grupo2.proyectoDigitalBooking.model.Category;
import com.grupo2.proyectoDigitalBooking.service.interfaces.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/Categories")
public class CategoryController {

        @Autowired
        private CategoryService categoryService;

        @Operation(summary = "Registrar nueva categoría")
        @PostMapping("/addCategory")
        public ResponseEntity<Object> addCategory(@RequestBody Category category) {
            return ResponseHandler.generateResponse("The category has been added", HttpStatus.CREATED, categoryService.addCategory(category));
        }

        @Operation(summary = "Buscar categoría por ID")
        @GetMapping("/searchCategory/{id}")
        public ResponseEntity<Object> searchCategory(@PathVariable Long id) {
            ResponseEntity<Object> response = null;

            if (id != null && categoryService.searchCategory(id).isPresent())
                response = ResponseHandler.generateResponse("The category has been found", HttpStatus.OK, categoryService.searchCategory(id));
            else
                response = ResponseHandler.generateResponse("The category has not been found", HttpStatus.NOT_FOUND, null);
            return response;
        }

        @Operation(summary = "Editar categoría")
        @PutMapping("/editCategory")
        public ResponseEntity<Object> editCategory(@RequestBody Category category) {
            ResponseEntity<Object> response = null;

            if (category.getId() != null && categoryService.searchCategory(category.getId()).isPresent())
                response = ResponseHandler.generateResponse("Category has been updated", HttpStatus.OK, categoryService.editCategory(category));
            else
                response = ResponseHandler.generateResponse("The category has not been found", HttpStatus.NOT_FOUND, null);
            return response;
        }

        @Operation(summary = "Eliminar categoría")
        @DeleteMapping("/deleteCategory/{id}")
        public ResponseEntity<Object> deleteCategory(@PathVariable Long id) throws Exception {

            ResponseEntity<Object> response = null;

            if (categoryService.searchCategory(id).isPresent()) {
                categoryService.deleteCategory(id);
                response = ResponseHandler.generateResponseNoContent();
            } else {
                response = ResponseHandler.generateResponse("The category has not been found", HttpStatus.NOT_FOUND, null);
            }
            return response;
        }

        @Operation(summary = "Listar todas las categorías")
        @GetMapping("/listCategories")
        public ResponseEntity<Object> listCategories() {
            return ResponseHandler.generateResponse("List of all categories", HttpStatus.OK, categoryService.listCategories());
        }
    }
