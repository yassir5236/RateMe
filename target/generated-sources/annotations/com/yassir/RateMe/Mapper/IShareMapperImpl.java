package com.yassir.RateMe.Mapper;

import com.yassir.RateMe.Dto.Category.EmbeddedCategoryDTO;
import com.yassir.RateMe.Dto.Place.PlaceResponseDTO;
import com.yassir.RateMe.Dto.Share.ShareRequestDTO;
import com.yassir.RateMe.Dto.Share.ShareResponseDTO;
import com.yassir.RateMe.Dto.User.EmbeddedUserDTO;
import com.yassir.RateMe.Dto.image.ImageResponseDTO;
import com.yassir.RateMe.Model.Entity.Category;
import com.yassir.RateMe.Model.Entity.Image;
import com.yassir.RateMe.Model.Entity.Place;
import com.yassir.RateMe.Model.Entity.Share;
import com.yassir.RateMe.Model.Entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-01T09:15:46+0000",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class IShareMapperImpl implements IShareMapper {

    @Override
    public Share toEntity(ShareRequestDTO ShareRequestDTO) {
        if ( ShareRequestDTO == null ) {
            return null;
        }

        Share share = new Share();

        share.setTitle( ShareRequestDTO.title() );

        return share;
    }

    @Override
    public ShareResponseDTO toResponseDto(Share share) {
        if ( share == null ) {
            return null;
        }

        Long id = null;
        String title = null;
        EmbeddedUserDTO user = null;
        PlaceResponseDTO place = null;

        id = share.getId();
        title = share.getTitle();
        user = userToEmbeddedUserDTO( share.getUser() );
        place = placeToPlaceResponseDTO( share.getPlace() );

        ShareResponseDTO shareResponseDTO = new ShareResponseDTO( id, title, user, place );

        return shareResponseDTO;
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

    protected EmbeddedCategoryDTO categoryToEmbeddedCategoryDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        Long id = null;
        String name = null;

        id = (long) category.getId();
        name = category.getName();

        EmbeddedCategoryDTO embeddedCategoryDTO = new EmbeddedCategoryDTO( id, name );

        return embeddedCategoryDTO;
    }

    protected PlaceResponseDTO placeToPlaceResponseDTO(Place place) {
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
        Double averageRating = null;
        EmbeddedUserDTO user = null;
        EmbeddedCategoryDTO category = null;

        id = place.getId();
        name = place.getName();
        description = place.getDescription();
        images = imageListToImageResponseDTOList( place.getImages() );
        address = place.getAddress();
        latitude = place.getLatitude();
        longitude = place.getLongitude();
        averageRating = place.getAverageRating();
        user = userToEmbeddedUserDTO( place.getUser() );
        category = categoryToEmbeddedCategoryDTO( place.getCategory() );

        PlaceResponseDTO placeResponseDTO = new PlaceResponseDTO( id, name, description, images, address, latitude, longitude, averageRating, user, category );

        return placeResponseDTO;
    }
}
