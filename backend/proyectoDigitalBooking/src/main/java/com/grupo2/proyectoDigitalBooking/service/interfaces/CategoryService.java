package com.grupo2.proyectoDigitalBooking.service.interfaces;

import com.grupo2.proyectoDigitalBooking.model.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryService {


    Category addCategory(Category category);

    Optional<Category> searchCategory(Long id);

    Category editCategory(Category category);

    void deleteCategory (Long id) throws Exception;

    List<Category> listCategories();
}
