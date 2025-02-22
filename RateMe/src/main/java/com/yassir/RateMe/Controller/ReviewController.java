package com.yassir.RateMe.Controller;


import com.yassir.RateMe.Dto.Review.ReviewRequestDTO;
import com.yassir.RateMe.Dto.Review.ReviewResponseDTO;
import com.yassir.RateMe.Service.IReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final IReviewService reviewService;

    @Autowired
    public ReviewController(IReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ReviewResponseDTO> createReview(@Valid @RequestBody ReviewRequestDTO reviewRequestDTO) {
        ReviewResponseDTO createdReview = reviewService.createReview(reviewRequestDTO);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> getReviewById(@PathVariable Long id) {
        ReviewResponseDTO review = reviewService.getReviewById(id);
        return ResponseEntity.ok(review);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> updateReview(
            @PathVariable Long id,
            @Valid @RequestBody ReviewRequestDTO reviewRequestDTO) {
        ReviewResponseDTO updatedReview = reviewService.updateReview(id, reviewRequestDTO);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponseDTO>> getAllReviews() {
        List<ReviewResponseDTO> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }


    @GetMapping("/paginated")
    public ResponseEntity<Page<ReviewResponseDTO>> getReviewsPaginatedAndSorted(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        Page<ReviewResponseDTO> reviews = reviewService.getReviewsPaginatedAndSorted(page, size, sortBy, direction);
        return ResponseEntity.ok(reviews);
    }
}