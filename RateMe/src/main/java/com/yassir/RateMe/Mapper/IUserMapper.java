    package com.yassir.RateMe.Mapper;

    import com.yassir.RateMe.Dto.User.UserRequestDTO;
    import com.yassir.RateMe.Dto.User.UserResponseDTO;
    import com.yassir.RateMe.Model.Entity.User;
    import org.mapstruct.Mapper;

    import java.util.List;

    @Mapper(componentModel = "spring")
    public interface IUserMapper {
        User toEntity (UserRequestDTO UserRequestDTO);
        UserResponseDTO toResponseDto (User user);
        // Add mapping for photos if needed
        default List<String> mapPhotos(List<String> photos) {
            return photos;
        }
    }



