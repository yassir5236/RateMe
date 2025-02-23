package com.yassir.RateMe.Service;

import com.yassir.RateMe.Dto.Review.ReviewRequestDTO;
import com.yassir.RateMe.Dto.Review.ReviewResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IReviewService {
    ReviewResponseDTO createReview(ReviewRequestDTO reviewRequestDTO);

    ReviewResponseDTO getReviewById(Long reviewId);

    List<ReviewResponseDTO> getReviewsByPlaceId(Long placeId);


    ReviewResponseDTO updateReview(Long id, ReviewRequestDTO reviewRequestDTO);

    List<ReviewResponseDTO> getAllReviews();

    void deleteReview(Long reviewId);

//    List<ReviewResponseDTO> searchReviews(String name, String location, Double minArea);

    Page<ReviewResponseDTO> getReviewsPaginatedAndSorted(int page, int size, String sortBy, String direction);

}
