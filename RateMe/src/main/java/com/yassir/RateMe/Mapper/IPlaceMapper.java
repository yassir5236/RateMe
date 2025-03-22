    package com.yassir.RateMe.Mapper;

    import com.yassir.RateMe.Dto.Place.PlaceRequestDTO;
    import com.yassir.RateMe.Dto.Place.PlaceResponseDTO;
    import com.yassir.RateMe.Model.Entity.Place;
    import org.mapstruct.Mapper;

    import java.util.List;

    @Mapper(componentModel = "spring")
    public interface IPlaceMapper {
        Place toEntity (PlaceRequestDTO PlaceRequestDTO);
        PlaceResponseDTO toResponseDto (Place place);
        default List<String> mapPhotos(List<String> photos) {
            return photos;
        }
    }



