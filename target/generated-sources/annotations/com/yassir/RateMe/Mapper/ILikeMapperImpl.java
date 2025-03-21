package com.yassir.RateMe.Mapper;

import com.yassir.RateMe.Dto.Like.LikeRequestDTO;
import com.yassir.RateMe.Dto.Like.LikeResponseDTO;
import com.yassir.RateMe.Dto.Place.EmbeddedPlaceDTO;
import com.yassir.RateMe.Dto.User.EmbeddedUserDTO;
import com.yassir.RateMe.Model.Entity.Like;
import com.yassir.RateMe.Model.Entity.Place;
import com.yassir.RateMe.Model.Entity.User;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-14T11:19:47+0000",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class ILikeMapperImpl implements ILikeMapper {

    @Override
    public Like toEntity(LikeRequestDTO LikeRequestDTO) {
        if ( LikeRequestDTO == null ) {
            return null;
        }

        Like like = new Like();

        return like;
    }

    @Override
    public LikeResponseDTO toResponseDto(Like like) {
        if ( like == null ) {
            return null;
        }

        Long id = null;
        EmbeddedUserDTO user = null;
        EmbeddedPlaceDTO place = null;

        id = like.getId();
        user = userToEmbeddedUserDTO( like.getUser() );
        place = placeToEmbeddedPlaceDTO( like.getPlace() );

        LikeResponseDTO likeResponseDTO = new LikeResponseDTO( id, user, place );

        return likeResponseDTO;
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

    protected EmbeddedPlaceDTO placeToEmbeddedPlaceDTO(Place place) {
        if ( place == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;
        String address = null;
        Double latitude = null;
        Double longitude = null;

        id = place.getId();
        name = place.getName();
        description = place.getDescription();
        address = place.getAddress();
        latitude = place.getLatitude();
        longitude = place.getLongitude();

        List<String> photos = null;

        EmbeddedPlaceDTO embeddedPlaceDTO = new EmbeddedPlaceDTO( id, name, description, photos, address, latitude, longitude );

        return embeddedPlaceDTO;
    }
}
