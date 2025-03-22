package com.yassir.RateMe.Mapper;

import com.yassir.RateMe.Dto.Place.EmbeddedPlaceDTO;
import com.yassir.RateMe.Dto.Review.ReviewRequestDTO;
import com.yassir.RateMe.Dto.Review.ReviewResponseDTO;
import com.yassir.RateMe.Dto.User.EmbeddedUserDTO;
import com.yassir.RateMe.Model.Entity.Place;
import com.yassir.RateMe.Model.Entity.Review;
import com.yassir.RateMe.Model.Entity.User;
import java.time.LocalDateTime;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-21T14:57:10+0000",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class IReviewMapperImpl implements IReviewMapper {

    @Override
    public Review toEntity(ReviewRequestDTO ReviewRequestDTO) {
        if ( ReviewRequestDTO == null ) {
            return null;
        }

        Review review = new Review();

        review.setRating( ReviewRequestDTO.rating() );
        review.setComment( ReviewRequestDTO.comment() );
        review.setCreatedDate( ReviewRequestDTO.createdDate() );

        return review;
    }

    @Override
    public ReviewResponseDTO toResponseDto(Review review) {
        if ( review == null ) {
            return null;
        }

        Long id = null;
        Double rating = null;
        String comment = null;
        LocalDateTime createdDate = null;
        EmbeddedUserDTO user = null;
        EmbeddedPlaceDTO place = null;

        id = review.getId();
        rating = review.getRating();
        comment = review.getComment();
        createdDate = review.getCreatedDate();
        user = userToEmbeddedUserDTO( review.getUser() );
        place = placeToEmbeddedPlaceDTO( review.getPlace() );

        ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO( id, rating, comment, createdDate, user, place );

        return reviewResponseDTO;
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
