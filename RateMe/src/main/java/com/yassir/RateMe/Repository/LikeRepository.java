package com.yassir.RateMe.Repository;

import com.yassir.RateMe.Model.Entity.Like;
import com.yassir.RateMe.Model.Entity.Place;
import com.yassir.RateMe.Model.Entity.Share;
import com.yassir.RateMe.Model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByUserId(Long userId);

    List<Like> findByUser(User user);


    @Query("SELECT l.place.id, COUNT(l) FROM Like l GROUP BY l.place.id")
    List<Object[]> countLikesForEachPlace();

    Optional<Like> findByUserIdAndPlaceId(Long userId, Long placeId);

    boolean existsByUserIdAndPlaceId(Long userId, Long placeId);

    Optional<Like> findByUserAndPlace(User user, Place place);

}
