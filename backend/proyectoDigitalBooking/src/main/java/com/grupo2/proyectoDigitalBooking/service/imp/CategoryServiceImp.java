package com.grupo2.proyectoDigitalBooking.service.imp;

import com.grupo2.proyectoDigitalBooking.model.*;
import com.grupo2.proyectoDigitalBooking.repository.ICategoryRepository;
import com.grupo2.proyectoDigitalBooking.service.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService {

    private final ICategoryRepository categoryRepository;


    @Autowired
    public CategoryServiceImp(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }


    public Optional<Category> searchCategory(Long id){
        return categoryRepository.findById(id);
    }


    public Category editCategory(Category category){
        return categoryRepository.save(category);
    }


    public void deleteCategory (Long id) throws Exception {
        Optional<Category> searchedCategory = searchCategory(id);
        if (searchedCategory.isPresent())
            categoryRepository.deleteById(id);
        else
            throw new Exception("The category has not been found");
    }


    public List<Category> listCategories(){

        List<Category> categories= categoryRepository.findAll();

        return categories;
    }
}

