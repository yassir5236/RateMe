package com.yassir.RateMe.Mapper;

import com.yassir.RateMe.Dto.Share.ShareRequestDTO;
import com.yassir.RateMe.Dto.Share.ShareResponseDTO;
import com.yassir.RateMe.Model.Entity.Review;
import com.yassir.RateMe.Model.Entity.Share;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface IShareMapper {
    Share toEntity (ShareRequestDTO ShareRequestDTO);
    ShareResponseDTO toResponseDto (Share share);
}
