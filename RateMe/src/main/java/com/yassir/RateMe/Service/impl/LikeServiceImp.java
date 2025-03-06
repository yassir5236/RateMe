package com.yassir.RateMe.Service.impl;

import com.yassir.RateMe.Dto.Like.LikeRequestDTO;
import com.yassir.RateMe.Dto.Like.LikeResponseDTO;
import com.yassir.RateMe.Dto.Place.EmbeddedPlaceDTO;
import com.yassir.RateMe.Dto.User.EmbeddedUserDTO;
import com.yassir.RateMe.Mapper.ILikeMapper;
import com.yassir.RateMe.Model.Entity.Like;
import com.yassir.RateMe.Model.Entity.Place;
import com.yassir.RateMe.Model.Entity.User;
import com.yassir.RateMe.Repository.LikeRepository;
import com.yassir.RateMe.Repository.PlaceRepository;
import com.yassir.RateMe.Repository.UserRepository;
import com.yassir.RateMe.Service.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeServiceImp implements ILikeService {

    private final LikeRepository likeRepository;
    private final ILikeMapper likeMapper;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;

    @Autowired
    public LikeServiceImp(LikeRepository likeRepository, ILikeMapper likeMapper,
                           UserRepository userRepository, PlaceRepository placeRepository) {
        this.likeRepository = likeRepository;
        this.likeMapper = likeMapper;
        this.userRepository = userRepository;
        this.placeRepository = placeRepository;
    }

    @Override
    public LikeResponseDTO createLike(LikeRequestDTO likeRequest) {
        User user = userRepository.findById(likeRequest.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Place place = placeRepository.findById(likeRequest.placeId())
                .orElseThrow(() -> new RuntimeException("Place not found"));

        Like like = likeMapper.toEntity(likeRequest);
        like.setUser(user);
        like.setPlace(place);
        Like savedLike = likeRepository.save(like);
        return likeMapper.toResponseDto(savedLike);
    }

    @Override
    public LikeResponseDTO toggleLike(LikeRequestDTO likeRequest) {
        User user = userRepository.findById(likeRequest.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Place place = placeRepository.findById(likeRequest.placeId())
                .orElseThrow(() -> new RuntimeException("Place not found"));

        Optional<Like> existingLike = likeRepository.findByUserAndPlace(user, place);

        if (existingLike.isPresent()) {
            likeRepository.delete(existingLike.get());
            return new LikeResponseDTO(null, null, null); // Or map to a DTO indicating removal
        } else {
            Like like = new Like();
            like.setUser(user);
            like.setPlace(place);
            Like savedLike = likeRepository.save(like);
            return likeMapper.toResponseDto(savedLike); // Assuming likeMapper exists and has this method
        }
    }




    @Override
    public List<Long> getLikedPlacesByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Récupérer les likes associés à cet utilisateur
        List<Like> likes = likeRepository.findByUser(user);

        // Retourner la liste des ids des places likées
        return likes.stream()
                .map(like -> like.getPlace().getId())
                .collect(Collectors.toList());
    }


    // LikeService.java



        public Map<Long, Long> getLikesCountForEachPlace() {
            List<Object[]> result = likeRepository.countLikesForEachPlace();

            // Create a map to store place ID and its like count
            Map<Long, Long> likesCountMap = new HashMap<>();
            for (Object[] row : result) {
                Long placeId = (Long) row[0];
                Long likeCount = (Long) row[1];
                likesCountMap.put(placeId, likeCount);
            }
            return likesCountMap;
        }



}