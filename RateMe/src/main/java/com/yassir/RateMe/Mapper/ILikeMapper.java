package com.yassir.RateMe.Mapper;

import com.yassir.RateMe.Dto.Like.LikeRequestDTO;
import com.yassir.RateMe.Dto.Like.LikeResponseDTO;
import com.yassir.RateMe.Model.Entity.Like;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface ILikeMapper {
    Like toEntity (LikeRequestDTO LikeRequestDTO);
    LikeResponseDTO toResponseDto (Like like);
}
