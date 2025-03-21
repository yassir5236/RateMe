package com.yassir.RateMe.Service;

import com.yassir.RateMe.Dto.Like.LikeRequestDTO;
import com.yassir.RateMe.Dto.Like.LikeResponseDTO;

import java.util.List;
import java.util.Map;

public interface ILikeService {
    LikeResponseDTO createLike(LikeRequestDTO likeRequest);


    public LikeResponseDTO toggleLike(LikeRequestDTO likeRequest) ;

    List<Long> getLikedPlacesByUser(Long userId);

    public Map<Long, Long> getLikesCountForEachPlace() ;

    }