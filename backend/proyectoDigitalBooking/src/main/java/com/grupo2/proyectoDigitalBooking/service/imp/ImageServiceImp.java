package com.grupo2.proyectoDigitalBooking.service.imp;

import com.grupo2.proyectoDigitalBooking.model.Image;
import com.grupo2.proyectoDigitalBooking.repository.IImageRepository;
import com.grupo2.proyectoDigitalBooking.service.interfaces.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImp implements ImageService {


    private final IImageRepository imageRepository;

    @Autowired
    public ImageServiceImp(IImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image addImage(Image image){return imageRepository.save(image);}

    public Optional<Image> searchImage(Long id){
        return imageRepository.findById(id);
    }

    public Image editImage(Image image){
        return imageRepository.save(image);
    }

    public void deleteImage(Long id) throws Exception {
        Optional<Image> searchedImage = searchImage(id);
        if (searchedImage.isPresent())
            imageRepository.deleteById(id);
        else
            throw new Exception("The image has not been found");
    }

    public List<Image> listImages(){
        List<Image>image= imageRepository.findAll();
        return image;
    }
}
