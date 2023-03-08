package com.grupo2.proyectoDigitalBooking.service.interfaces;

import com.grupo2.proyectoDigitalBooking.model.Image;
import java.util.List;
import java.util.Optional;

public interface ImageService {

    Image addImage(Image image);

    Optional<Image> searchImage(Long id);

    Image editImage(Image image);

    void deleteImage(Long id) throws Exception;

    List<Image> listImages();
}
