package com.yassir.RateMe.Service;



import com.yassir.RateMe.Dto.Place.PlaceRequestDTO;
import com.yassir.RateMe.Dto.Place.PlaceResponseDTO;

import java.util.List;

public interface IPlaceService {
    PlaceResponseDTO createPlace(PlaceRequestDTO placeRequestDTO);

    PlaceResponseDTO getPlaceById(Long placeId);

    PlaceResponseDTO updatePlace(Long id, PlaceRequestDTO placeRequestDTO);

    List<PlaceResponseDTO> getAllPlaces();

    void deletePlace(Long placeId);
}
