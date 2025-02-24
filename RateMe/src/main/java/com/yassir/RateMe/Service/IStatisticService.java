package com.yassir.RateMe.Service;

import java.util.Map;

public interface IStatisticService {
    Map<String, Long> getStatistics();
    public Map<String, Object> getPlacesGrowth();


    }
