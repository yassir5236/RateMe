package com.yassir.RateMe.Service.impl;

import com.yassir.RateMe.Dto.Review.ReviewRequestDTO;
import com.yassir.RateMe.Dto.Review.ReviewResponseDTO;
import com.yassir.RateMe.Mapper.IReviewMapper;
import com.yassir.RateMe.Model.Entity.Review;
import com.yassir.RateMe.Repository.ReviewRepository;
import com.yassir.RateMe.Service.IReviewService;
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


    @Autowired
    public ReviewServiceImp(ReviewRepository reviewRepository, IReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;

    }


    @Override
    public ReviewResponseDTO createReview(ReviewRequestDTO reviewRequestDTO) {
        Review review = reviewMapper.toEntity(reviewRequestDTO);
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
    public ReviewResponseDTO updateReview(Long id, ReviewRequestDTO reviewRequestDTO) {
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review not found with ID: " + id));
//        existingReview.setName(reviewRequestDTO.name());

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
