package com.yassir.RateMe.Controller;

import com.yassir.RateMe.Dto.Like.LikeRequestDTO;
import com.yassir.RateMe.Dto.Like.LikeResponseDTO;
import com.yassir.RateMe.Service.ILikeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    private final ILikeService likeService;

    @Autowired
    public LikeController(ILikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public ResponseEntity<LikeResponseDTO> createLike(@Valid @RequestBody LikeRequestDTO likeRequestDTO) {
        System.out.println("Received LikeRequestDTO: " + likeRequestDTO);

        LikeResponseDTO createdLike = likeService.createLike(likeRequestDTO);
        return new ResponseEntity<>(createdLike, HttpStatus.CREATED);
    }

    @PostMapping("/toggle")
    public ResponseEntity<LikeResponseDTO> toggleLike(@Valid @RequestBody LikeRequestDTO likeRequestDTO) {
        LikeResponseDTO response = likeService.toggleLike(likeRequestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Long>> getLikedPlaces(@PathVariable Long userId) {
        List<Long> likedPlaceIds = likeService.getLikedPlacesByUser(userId);
        return ResponseEntity.ok(likedPlaceIds);
    }



    @GetMapping("/places/likes")
    public ResponseEntity<Map<Long, Long>> getLikesCountForEachPlace() {
        Map<Long, Long> likesCount = likeService.getLikesCountForEachPlace();
        return ResponseEntity.ok(likesCount);
    }


}