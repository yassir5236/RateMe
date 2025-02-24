package com.yassir.RateMe.Controller;


import com.yassir.RateMe.Service.IStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final IStatisticService statisticsService;

    @Autowired
    public StatisticsController(IStatisticService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Long>> getStatistics() {
        Map<String, Long> stats = statisticsService.getStatistics();
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/places-growth")
    public ResponseEntity<Map<String, Object>> getPlacesGrowth() {
        Map<String, Object> growthData = statisticsService.getPlacesGrowth();
        return ResponseEntity.ok(growthData);
    }
}