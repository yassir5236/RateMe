package com.yassir.RateMe.Mapper;

import com.yassir.RateMe.Dto.Review.ReviewRequestDTO;
import com.yassir.RateMe.Dto.Review.ReviewResponseDTO;
import com.yassir.RateMe.Model.Entity.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IReviewMapper {
        Review toEntity (ReviewRequestDTO ReviewRequestDTO);
        ReviewResponseDTO toResponseDto (Review review);


}
