package com.yassir.RateMe.Repository;

import com.yassir.RateMe.Model.Entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    @Query("SELECT p FROM Place p LEFT JOIN FETCH p.category WHERE p.id = :id")
    Optional<Place> findByIdWithCategory(Long id);


    @Query("SELECT p.createdAt, COUNT(p) " +
            "FROM Place p " +
            "WHERE p.createdAt BETWEEN :startDate AND :endDate " +
            "GROUP BY p.createdAt")
    List<Object[]> countPlacesByDay(@Param("startDate") LocalDate startDate,
                                    @Param("endDate") LocalDate endDate);


    List<Place> findAllByOrderByCreatedAtDesc();
    @Query("SELECT p FROM Place p ORDER BY p.id DESC")
    List<Place> findLatestPlaces();



}
