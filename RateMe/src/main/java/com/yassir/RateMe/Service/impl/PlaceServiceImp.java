package com.yassir.RateMe.Service.impl;

import com.yassir.RateMe.Dto.Place.PlaceRequestDTO;
import com.yassir.RateMe.Dto.Place.PlaceResponseDTO;
import com.yassir.RateMe.Mapper.IPlaceMapper;
import com.yassir.RateMe.Model.Entity.Category;
import com.yassir.RateMe.Model.Entity.Image;
import com.yassir.RateMe.Model.Entity.Place;
import com.yassir.RateMe.Model.Entity.User;
import com.yassir.RateMe.Repository.CategoryRepository;
import com.yassir.RateMe.Repository.PlaceRepository;
import com.yassir.RateMe.Repository.UserRepository;
import com.yassir.RateMe.Service.FileUploader;
import com.yassir.RateMe.Service.IPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaceServiceImp implements IPlaceService {


    private final PlaceRepository placeRepository;
    private final IPlaceMapper placeMapper;
    private final CategoryRepository categoryRepository;
    private final FileUploader fileUploader;
    private final UserRepository userRepository;


    @Autowired
    public PlaceServiceImp(PlaceRepository placeRepository, IPlaceMapper placeMapper, CategoryRepository categoryRepository, FileUploader fileUploader , UserRepository userRepository) {
        this.placeRepository = placeRepository;
        this.placeMapper = placeMapper;
        this.categoryRepository = categoryRepository;
        this.fileUploader = fileUploader;
        this.userRepository = userRepository;

    }




    public PlaceResponseDTO createPlace(PlaceRequestDTO placeRequest, List<MultipartFile> images) {
        Place place = placeMapper.toEntity(placeRequest);
        Category category = categoryRepository.findById(placeRequest.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        User user = userRepository.findById(placeRequest.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Image> imageUrls = images.stream()
                .map(image -> fileUploader.upload(image))
                .map(Image::of)
                .peek(image -> image.setPlace(place))
                .collect(Collectors.toList());

        place.setImages(imageUrls);
        place.setCategory(category);
        place.setUser(user);
        System.out.println(imageUrls);
        return placeMapper.toResponseDto(placeRepository.save(place));
    }


    public PlaceResponseDTO getPlaceById(Long placeId) {
        Place place = placeRepository.findByIdWithCategory(placeId)
                .orElseThrow(() -> new IllegalArgumentException("Place non trouvée"));
        return placeMapper.toResponseDto(place);
    }




    @Override
    public PlaceResponseDTO updatePlace(Long id, PlaceRequestDTO placeRequestDTO, List<MultipartFile> images) {
        Place existingPlace = placeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Place not found with ID: " + id));

        existingPlace.setName(placeRequestDTO.name());
        existingPlace.setDescription(placeRequestDTO.description());
        existingPlace.setAddress(placeRequestDTO.address());
        existingPlace.setLatitude(placeRequestDTO.latitude());
        existingPlace.setLongitude(placeRequestDTO.longitude());

        Category category = categoryRepository.findById(placeRequestDTO.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        existingPlace.setCategory(category);

        if (images != null && !images.isEmpty()) {
            List<Image> newImages = images.stream()
                    .map(image -> fileUploader.upload(image))
                    .map(Image::of)
                    .peek(image -> image.setPlace(existingPlace))
                    .collect(Collectors.toList());
            existingPlace.getImages().addAll(newImages);
        }

        return placeMapper.toResponseDto(placeRepository.save(existingPlace));
    }


    @Override
    public List<PlaceResponseDTO> getAllPlaces() {
        List<Place> places = placeRepository.findLatestPlaces();
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
