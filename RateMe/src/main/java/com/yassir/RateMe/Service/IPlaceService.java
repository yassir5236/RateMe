package com.yassir.RateMe.Service;



import com.yassir.RateMe.Dto.Place.PlaceRequestDTO;
import com.yassir.RateMe.Dto.Place.PlaceResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPlaceService {
//    PlaceResponseDTO createPlace(PlaceRequestDTO placeRequestDTO);
 PlaceResponseDTO createPlace(PlaceRequestDTO placeRequest, List<MultipartFile> images) ;

    PlaceResponseDTO getPlaceById(Long placeId);

//    PlaceResponseDTO updatePlace(Long id, PlaceRequestDTO placeRequestDTO);
 PlaceResponseDTO updatePlace(Long id, PlaceRequestDTO placeRequestDTO, List<MultipartFile> images) ;
    List<PlaceResponseDTO> getAllPlaces();

    void deletePlace(Long placeId);
}
