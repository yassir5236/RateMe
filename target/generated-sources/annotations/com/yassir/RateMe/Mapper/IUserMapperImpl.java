package com.yassir.RateMe.Mapper;

import com.yassir.RateMe.Dto.Category.EmbeddedCategoryDTO;
import com.yassir.RateMe.Dto.Place.EmbeddedPlaceDTO;
import com.yassir.RateMe.Dto.User.UserRequestDTO;
import com.yassir.RateMe.Dto.User.UserResponseDTO;
import com.yassir.RateMe.Model.Entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-06T11:04:22+0000",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class IUserMapperImpl implements IUserMapper {

    @Override
    public User toEntity(UserRequestDTO UserRequestDTO) {
        if ( UserRequestDTO == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( UserRequestDTO.username() );
        user.setEmail( UserRequestDTO.email() );
        user.setPassword( UserRequestDTO.password() );
        user.setLocation( UserRequestDTO.location() );
        user.setProfilePicture( UserRequestDTO.profilePicture() );
        user.setBio( UserRequestDTO.bio() );
        user.setRole( UserRequestDTO.role() );

        return user;
    }

    @Override
    public UserResponseDTO toResponseDto(User user) {
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

        EmbeddedCategoryDTO category = null;
        EmbeddedPlaceDTO place = null;

        UserResponseDTO userResponseDTO = new UserResponseDTO( id, username, email, password, location, profilePicture, bio, role, category, place );

        return userResponseDTO;
    }
}
