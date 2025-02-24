package com.yassir.RateMe.Service.impl;

import com.yassir.RateMe.Dto.Place.EmbeddedPlaceDTO;
import com.yassir.RateMe.Dto.Review.ReviewRequestDTO;
import com.yassir.RateMe.Dto.Review.ReviewResponseDTO;
import com.yassir.RateMe.Dto.User.EmbeddedUserDTO;
import com.yassir.RateMe.Mapper.IReviewMapper;
import com.yassir.RateMe.Model.Entity.Place;
import com.yassir.RateMe.Model.Entity.Review;
import com.yassir.RateMe.Model.Entity.User;
import com.yassir.RateMe.Repository.PlaceRepository;
import com.yassir.RateMe.Repository.ReviewRepository;
import com.yassir.RateMe.Repository.UserRepository;
import com.yassir.RateMe.Service.IPlaceService;
import com.yassir.RateMe.Service.IReviewService;
import com.yassir.RateMe.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class ReviewServiceImp implements IReviewService {


    private final ReviewRepository reviewRepository;
    private final IReviewMapper reviewMapper;
    private final UserService userService;
    private final IPlaceService placeService;
    private final PlaceRepository placeRepository;
    private final UserRepository UserRepository;


    @Autowired
    public ReviewServiceImp(ReviewRepository reviewRepository, IReviewMapper reviewMapper, UserService userService, IPlaceService placeService, PlaceRepository placeRepository, UserRepository UserRepository) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
        this.userService = userService;
        this.placeService = placeService;
        this.placeRepository = placeRepository;
        this.UserRepository = UserRepository;

    }


    @Override
    public ReviewResponseDTO createReview(ReviewRequestDTO reviewRequestDTO) {
        User user = UserRepository.findById(reviewRequestDTO.userId())
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        Place place = placeRepository.findById(reviewRequestDTO.placeId())
                .orElseThrow(() -> new RuntimeException("place not found"));

        Review review = reviewMapper.toEntity(reviewRequestDTO);
        review.setUser(user);
        review.setPlace(place);
        Review savedReview = reviewRepository.save(review);
        return reviewMapper.toResponseDto(savedReview);
    }


    @Override
    public ReviewResponseDTO getReviewById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Review not found with ID: " + reviewId));
        return reviewMapper.toResponseDto(review);
    }

    @Override
    public List<ReviewResponseDTO> getReviewsByPlaceId(Long placeId) {
        List<Review> reviews = reviewRepository.findByPlaceId(placeId);
        return reviews.stream()
                .map(review -> new ReviewResponseDTO(
                        review.getId(),
                        review.getRating(),
                        review.getComment(),
                        review.getCreatedDate(),
                        new EmbeddedUserDTO(review.getUser().getId(), review.getUser().getUsername(), review.getUser().getProfilePicture()), // Adapte selon ton modèle User
                        new EmbeddedPlaceDTO(review.getPlace().getId(), review.getPlace().getName())   // Adapte selon ton modèle Place
                ))

                .collect(Collectors.toList());
    }


    @Override
    public ReviewResponseDTO updateReview(Long id, ReviewRequestDTO reviewRequestDTO) {
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review not found with ID: " + id));
//        existingReview.setName(reviewRequestDTO.name());
        User ExistingUser = UserRepository.findById(reviewRequestDTO.userId())
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        Place ExistingPlace = placeRepository.findById(reviewRequestDTO.placeId())
                .orElseThrow(() -> new RuntimeException("place not found"));
        existingReview.setUser(ExistingUser);
        existingReview.setPlace(ExistingPlace);
        existingReview.setComment(reviewRequestDTO.comment());
        existingReview.setCreatedDate(reviewRequestDTO.createdDate());
        existingReview.setRating(reviewRequestDTO.rating());

        Review updatedReview = reviewRepository.save(existingReview);
        return reviewMapper.toResponseDto(updatedReview);
    }

    @Override
    public List<ReviewResponseDTO> getAllReviews() {
        List<Review> reviews = (List<Review>) reviewRepository.findAll();
        return reviews.stream()
                .map(reviewMapper::toResponseDto)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteReview(Long reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            throw new IllegalArgumentException("Review not found with ID: " + reviewId);
        }
        reviewRepository.deleteById(reviewId);
    }


//    @Override
//    public List<ReviewResponseDTO> searchReviews(String name, String location, Double minArea) {
//        Specification<Review> spec = ReviewSpecification.searchReviews(name, location, minArea);
//        List<Review> reviews = reviewRepository.findAll(spec);
//        return reviews.stream()
//                .map(reviewMapper::toResponseDto)
//                .collect(Collectors.toList());
//    }


    @Override
    public Page<ReviewResponseDTO> getReviewsPaginatedAndSorted(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Review> reviewPage = reviewRepository.findAll(pageable);
        return reviewPage.map(reviewMapper::toResponseDto);
    }


}
