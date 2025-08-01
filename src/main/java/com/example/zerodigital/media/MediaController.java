package com.example.zerodigital.media;

import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shared.CustomizedResponse;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/media")
public class MediaController {

    @Autowired
    private MediaService mediaService;

  @GetMapping("/movies")
    public ResponseEntity getAllMovies() {
      var response = new CustomizedResponse( " A list of all movies ", mediaService.getByType("movie"));
    return new ResponseEntity(response, HttpStatus.OK);
  }
    @GetMapping("/tvshows")
    public ResponseEntity getAllTVShows() {
        var response = new CustomizedResponse( " A list of all tvshows ", mediaService.getByType("tvshow"));
        return new ResponseEntity(response, HttpStatus.OK);
    }
    @GetMapping("/search/{title}")
    public ResponseEntity getByTitle(@PathVariable("title") String title) {
        List<Media> foundMedia = mediaService.searchByTitle(title);
        if(foundMedia.isEmpty()){
            var response = new CustomizedResponse( " No media for title ", Collections.singletonList(title));

            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
        }
        var response = new CustomizedResponse( " A list of all users ", mediaService.searchByTitle(title));
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/featured")
    public ResponseEntity getFeaturedByType(@RequestParam String type) {

        if (!type.equalsIgnoreCase("movie") && !type.equalsIgnoreCase("tvshow")) {
            var response = new CustomizedResponse("Invalid type", Collections.singletonList("Type must be 'movie' or 'tvshow'"));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        var featuredList = mediaService.getFeaturedByType(type);
        var response = new CustomizedResponse("Featured " + type + "s", featuredList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity getMediaById(@PathVariable String id) {

        if (!ObjectId.isValid(id)) {
            var error = new CustomizedResponse("Invalid media ID format", Collections.singletonList(id));
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        Media media = mediaService.getById(id);
        if (media == null) {
            var error = new CustomizedResponse("Media not found", Collections.singletonList("No media with ID: " + id));
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        var response = new CustomizedResponse("Media found", Collections.singletonList(media));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMedia(
            @PathVariable String id,
            @Valid @RequestBody MediaUpdateDTO dto,
            BindingResult result
    ) {
        if (!ObjectId.isValid(id)) {
            var error = new CustomizedResponse(
                    "Invalid media ID format",
                    Collections.singletonList(id)
            );
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();

            return new ResponseEntity<>(
                    new CustomizedResponse("Validation failed", errors),
                    HttpStatus.BAD_REQUEST
            );
        }

        var updated = mediaService.updateMedia(id, dto);
        var response = new CustomizedResponse("Media updated!", Collections.singletonList(updated));
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteMedia(@PathVariable String id) {
      if (!ObjectId.isValid(id)) {
          var error = new CustomizedResponse("Invalid media ID format", Collections.singletonList(id));
          return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
      }
      Media media = mediaService.getById(id);
      if (media == null) {
          var error = new CustomizedResponse("No media with ID: " + id, Collections.singletonList(id));
          return new ResponseEntity(error, HttpStatus.NOT_FOUND);
      }
      var response = new CustomizedResponse("Media deleted!", Collections.singletonList(mediaService.deleteById(id)));
      return new ResponseEntity(response, HttpStatus.OK);
    }

}
