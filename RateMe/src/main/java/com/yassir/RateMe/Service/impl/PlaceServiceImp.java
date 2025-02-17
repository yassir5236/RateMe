package com.yassir.RateMe.Service.impl;

import com.yassir.RateMe.Dto.Place.PlaceRequestDTO;
import com.yassir.RateMe.Dto.Place.PlaceResponseDTO;
import com.yassir.RateMe.Mapper.IPlaceMapper;
import com.yassir.RateMe.Model.Entity.Category;
import com.yassir.RateMe.Model.Entity.Place;
import com.yassir.RateMe.Repository.CategoryRepository;
import com.yassir.RateMe.Repository.PlaceRepository;
import com.yassir.RateMe.Service.IPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaceServiceImp implements IPlaceService {


    private final PlaceRepository placeRepository;
    private final IPlaceMapper placeMapper;
    private final CategoryRepository categoryRepository;


    @Autowired
    public PlaceServiceImp(PlaceRepository placeRepository, IPlaceMapper placeMapper, CategoryRepository categoryRepository) {
        this.placeRepository = placeRepository;
        this.placeMapper = placeMapper;
        this.categoryRepository = categoryRepository;

    }


    @Override
    public PlaceResponseDTO createPlace(PlaceRequestDTO placeRequestDTO) {
        Place place = placeMapper.toEntity(placeRequestDTO);

        Category category = categoryRepository.findById(placeRequestDTO.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));



        place.setCategory(category);
        Place savedPlace = placeRepository.save(place);
        return placeMapper.toResponseDto(savedPlace);
    }


    @Override
    public PlaceResponseDTO getPlaceById(Long placeId) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new IllegalArgumentException("Place not found with ID: " + placeId));
        return placeMapper.toResponseDto(place);
    }

    @Override
    public PlaceResponseDTO updatePlace(Long id, PlaceRequestDTO placeRequestDTO) {
        Place existingPlace = placeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Place not found with ID: " + id));

        Category category = categoryRepository.findById(placeRequestDTO.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));


        existingPlace.setCategory(category);
        existingPlace.setName(placeRequestDTO.name());

        Place updatedPlace = placeRepository.save(existingPlace);
        return placeMapper.toResponseDto(updatedPlace);
    }

    @Override
    public List<PlaceResponseDTO> getAllPlaces() {
        List<Place> places = (List<Place>) placeRepository.findAll();
        return places.stream()
                .map(placeMapper::toResponseDto)
                .collect(Collectors.toList());
    }


    @Override
    public void deletePlace(Long placeId) {
        if (!placeRepository.existsById(placeId)) {
            throw new IllegalArgumentException("Place not found with ID: " + placeId);
        }
        placeRepository.deleteById(placeId);
    }


}
