package com.yassir.RateMe.Controller;

import com.yassir.RateMe.Dto.Place.PlaceRequestDTO;
import com.yassir.RateMe.Dto.Place.PlaceResponseDTO;
import com.yassir.RateMe.Service.IPlaceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/places")
public class PlaceController {

    private final IPlaceService placeService;

    @Autowired
    public PlaceController(IPlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping
    public ResponseEntity<PlaceResponseDTO> createPlace(@Valid @RequestBody PlaceRequestDTO placeRequestDTO) {
        PlaceResponseDTO createdPlace = placeService.createPlace(placeRequestDTO);
        return new ResponseEntity<>(createdPlace, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaceResponseDTO> getPlaceById(@PathVariable Long id) {
        PlaceResponseDTO place = placeService.getPlaceById(id);
        return ResponseEntity.ok(place);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlaceResponseDTO> updatePlace(
            @PathVariable Long id,
            @Valid @RequestBody PlaceRequestDTO placeRequestDTO) {
        PlaceResponseDTO updatedPlace = placeService.updatePlace(id, placeRequestDTO);
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
        return ResponseEntity.ok(places);
    }




}
