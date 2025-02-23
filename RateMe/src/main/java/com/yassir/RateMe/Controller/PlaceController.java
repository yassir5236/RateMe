package com.yassir.RateMe.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yassir.RateMe.Dto.Place.PlaceRequestDTO;
import com.yassir.RateMe.Dto.Place.PlaceResponseDTO;
import com.yassir.RateMe.Service.IPlaceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper; // Import ObjectMapper


import java.util.List;

@RestController
@RequestMapping("/api/places")
public class PlaceController {

    private final IPlaceService placeService;
    private final ObjectMapper objectMapper; // Declare ObjectMapper

    @Autowired
    public PlaceController(IPlaceService placeService , ObjectMapper objectMapper) {
        this.placeService = placeService;
        this.objectMapper = objectMapper;
    }



    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PlaceResponseDTO> createPlace(
            @RequestPart("place") String placeJson,
            @RequestPart("images") List<MultipartFile> images
    ) throws JsonProcessingException {
//        System.out.println(placeJson);
        PlaceRequestDTO placeRequest = objectMapper.readValue(placeJson, PlaceRequestDTO.class);
        PlaceResponseDTO createdPlace = placeService.createPlace(placeRequest, images);
        return new ResponseEntity<>(createdPlace, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PlaceResponseDTO> getPlaceById(@PathVariable Long id) {
        PlaceResponseDTO place = placeService.getPlaceById(id);
        return ResponseEntity.ok(place);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<PlaceResponseDTO> updatePlace(
//            @PathVariable Long id,
//            @Valid @RequestBody PlaceRequestDTO placeRequestDTO) {
//        PlaceResponseDTO updatedPlace = placeService.updatePlace(id, placeRequestDTO);
//        return ResponseEntity.ok(updatedPlace);
//    }


    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PlaceResponseDTO> updatePlace(
            @PathVariable Long id,
            @RequestPart("place") @Valid PlaceRequestDTO placeRequestDTO,
            @RequestPart(value = "images", required = false) List<MultipartFile> images) {

        PlaceResponseDTO updatedPlace = placeService.updatePlace(id, placeRequestDTO, images);
        return ResponseEntity.ok(updatedPlace);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlace(@PathVariable Long id) {
        placeService.deletePlace(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PlaceResponseDTO>> getAllPlaces() {
        List<PlaceResponseDTO> places = placeService.getAllPlaces();
//        System.out.println(places);
        return ResponseEntity.ok(places);
    }




}
