package com.yassir.RateMe.Service.impl;



import com.yassir.RateMe.Repository.PlaceRepository;
import com.yassir.RateMe.Repository.ReviewRepository;
import com.yassir.RateMe.Repository.UserRepository;
import com.yassir.RateMe.Service.IStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service
public class ServiceStatisticsImpl implements IStatisticService {

    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public ServiceStatisticsImpl(UserRepository userRepository, PlaceRepository placeRepository, ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.placeRepository = placeRepository;
        this.reviewRepository = reviewRepository;
    }

    public Map<String, Long> getStatistics() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("totalUsers", userRepository.count());
        stats.put("totalPlaces", placeRepository.count());
        stats.put("totalReviews", reviewRepository.count());
        return stats;
    }



    public Map<String, Object> getPlacesGrowth() {
        Map<String, Object> growthData = new HashMap<>();

        LocalDate today = LocalDate.now();
        LocalDate ninetyDaysAgo = today.minusDays(90);

        List<Object[]> results = placeRepository.countPlacesByDay(ninetyDaysAgo, today);

        List<String> labels = new ArrayList<>();
        List<Long> data = new ArrayList<>();

        for (Object[] result : results) {
            LocalDate date = (LocalDate) result[0];
            Long count = (Long) result[1];

            System.out.println("Row: " + date + " -> " + count);
            labels.add(date.toString());
            data.add(count);
        }

        growthData.put("labels", labels);
        growthData.put("data", data);

        System.out.println("Final Growth Data: " + growthData);

        return growthData;
    }


}