package br.senac.tialejo.Services;

import br.senac.tialejo.model.Image;
import br.senac.tialejo.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService{
    @Autowired
    private ImageRepository imageRepository;
    @Override
    public Image create(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public List<Image> viewAll() {
        return (List<Image>) imageRepository.findAll();

    }

    @Override
    public Image viewById(long id) {
        return imageRepository.findById(id).get();
    }
}

