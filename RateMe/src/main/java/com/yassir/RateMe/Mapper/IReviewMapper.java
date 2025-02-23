package com.yassir.RateMe.Mapper;

import com.yassir.RateMe.Dto.Review.ReviewRequestDTO;
import com.yassir.RateMe.Dto.Review.ReviewResponseDTO;
import com.yassir.RateMe.Model.Entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IReviewMapper {
        Review toEntity (ReviewRequestDTO ReviewRequestDTO);
        ReviewResponseDTO toResponseDto (Review review);


}

//@Mapper(componentModel = "spring")
//public interface IReviewMapper {
//        Review toEntity(ReviewRequestDTO reviewRequestDTO);
//
//        @Mapping(target = "place", expression = "java(new EmbeddedPlaceDTO(review.getPlace().getId(), review.getPlace().getName()))")
//        @Mapping(target = "user", expression = "java(new EmbeddedUserDTO(review.getUser().getId(), review.getUser().getUsername()))")
//        ReviewResponseDTO toResponseDto(Review review);
//}

