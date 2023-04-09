package com.triple.task.travel.city.application.model;

import com.triple.task.travel.city.domain.City;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CityQuery {
    private Long id;
    private String name;
    private String continent;
    private String country;

    public static CityQuery of(City city) {
        return CityQuery.builder()
                .id(city.getId())
                .name(city.getName())
                .continent(city.getContinent())
                .country(city.getCountry())
                .build();
    }
}
