package com.yassir.RateMe.Repository;

import com.yassir.RateMe.Model.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
