package com.triple.task.travel.city.application.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CityQueryList {
    private int count;
    private List<CityQuery> cities;

    public static CityQueryList of(List<CityQuery> cities) {
        return CityQueryList.builder().count(cities.size()).cities(cities).build();
    }
}
