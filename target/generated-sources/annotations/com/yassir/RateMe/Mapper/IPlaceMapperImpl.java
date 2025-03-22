package com.yassir.RateMe.Mapper;

import com.yassir.RateMe.Dto.Category.EmbeddedCategoryDTO;
import com.yassir.RateMe.Dto.Place.PlaceRequestDTO;
import com.yassir.RateMe.Dto.Place.PlaceResponseDTO;
import com.yassir.RateMe.Dto.User.EmbeddedUserDTO;
import com.yassir.RateMe.Dto.image.ImageResponseDTO;
import com.yassir.RateMe.Model.Entity.Category;
import com.yassir.RateMe.Model.Entity.Image;
import com.yassir.RateMe.Model.Entity.Place;
import com.yassir.RateMe.Model.Entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-21T14:57:10+0000",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class IPlaceMapperImpl implements IPlaceMapper {

    @Override
    public Place toEntity(PlaceRequestDTO PlaceRequestDTO) {
        if ( PlaceRequestDTO == null ) {
            return null;
        }

        Place place = new Place();

        place.setName( PlaceRequestDTO.name() );
        place.setDescription( PlaceRequestDTO.description() );
        place.setAddress( PlaceRequestDTO.address() );
        place.setLatitude( PlaceRequestDTO.latitude() );
        place.setLongitude( PlaceRequestDTO.longitude() );

        return place;
    }

    @Override
    public PlaceResponseDTO toResponseDto(Place place) {
        if ( place == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;
        List<ImageResponseDTO> images = null;
        String address = null;
        Double latitude = null;
        Double longitude = null;
        EmbeddedUserDTO user = null;
        EmbeddedCategoryDTO category = null;

        id = place.getId();
        name = place.getName();
        description = place.getDescription();
        images = imageListToImageResponseDTOList( place.getImages() );
        address = place.getAddress();
        latitude = place.getLatitude();
        longitude = place.getLongitude();
        user = userToEmbeddedUserDTO( place.getUser() );
        category = categoryToEmbeddedCategoryDTO( place.getCategory() );

        PlaceResponseDTO placeResponseDTO = new PlaceResponseDTO( id, name, description, images, address, latitude, longitude, user, category );

        return placeResponseDTO;
    }

    protected ImageResponseDTO imageToImageResponseDTO(Image image) {
        if ( image == null ) {
            return null;
        }

        String path = null;

        path = image.getPath();

        ImageResponseDTO imageResponseDTO = new ImageResponseDTO( path );

        return imageResponseDTO;
    }

    protected List<ImageResponseDTO> imageListToImageResponseDTOList(List<Image> list) {
        if ( list == null ) {
            return null;
        }

        List<ImageResponseDTO> list1 = new ArrayList<ImageResponseDTO>( list.size() );
        for ( Image image : list ) {
            list1.add( imageToImageResponseDTO( image ) );
        }

        return list1;
    }

    protected EmbeddedUserDTO userToEmbeddedUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        Long id = null;
        String username = null;
        String email = null;
        String password = null;
        String location = null;
        String profilePicture = null;
        String bio = null;
        String role = null;

        id = user.getId();
        username = user.getUsername();
        email = user.getEmail();
        password = user.getPassword();
        location = user.getLocation();
        profilePicture = user.getProfilePicture();
        bio = user.getBio();
        role = user.getRole();

        EmbeddedUserDTO embeddedUserDTO = new EmbeddedUserDTO( id, username, email, password, location, profilePicture, bio, role );

        return embeddedUserDTO;
    }

    protected EmbeddedCategoryDTO categoryToEmbeddedCategoryDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        Long id = null;
        String name = null;

        id = category.getId();
        name = category.getName();

        EmbeddedCategoryDTO embeddedCategoryDTO = new EmbeddedCategoryDTO( id, name );

        return embeddedCategoryDTO;
    }
}
