package com.example.zerodigital.media;

import com.example.zerodigital.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    public List<Media> getAll() {
        return mediaRepository.findAll();
    }

    public List<Media> getByType(String type) {
        return mediaRepository.findByType(type.toLowerCase());
    }

    public List<Media> searchByTitle(String title) {
        return mediaRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Media> getFeaturedByType(String type) {
        return mediaRepository.findByTypeAndFeaturedTrue(type.toLowerCase());
    }

    public Media getById(String id) {
        return mediaRepository.findById(id).orElse(null);
    }

    public Media updateMedia(String id, MediaUpdateDTO dto) {
        Optional<Media> optionalMedia = mediaRepository.findById(id);
        if (optionalMedia.isEmpty()) {
            throw new RuntimeException("Media with ID " + id + " not found.");
        }
        Media media = optionalMedia.get();

        media.setTitle(dto.getTitle());
        media.setYear(dto.getYear());
        media.setGenre(dto.getGenre());
        media.setPrice(dto.getPrice());
        media.setSynopsis(dto.getSynopsis());
        media.setType(dto.getType());
        media.setSmallPoster(dto.getSmallPoster());
        media.setPoster(dto.getPoster());
        media.setRentPrice(dto.getRentPrice());
        media.setPurchasePrice(dto.getPurchasePrice());
        media.setFeatured(dto.getFeatured());

        return mediaRepository.save(media);
    }

    public boolean deleteById(String id) {
        if (!mediaRepository.existsById(id)) return false;
        mediaRepository.deleteById(id);
        return true;
    }
}
