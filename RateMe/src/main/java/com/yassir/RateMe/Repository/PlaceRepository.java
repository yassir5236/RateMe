package com.yassir.RateMe.Repository;

import com.yassir.RateMe.Model.Entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    @Query("SELECT p FROM Place p LEFT JOIN FETCH p.category WHERE p.id = :id")
    Optional<Place> findByIdWithCategory(Long id);
}
