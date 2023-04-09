package com.triple.task.travel.trip.application.model;

import com.triple.task.travel.city.application.model.CityQuery;
import com.triple.task.travel.trip.domain.Trip;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TripQuery {
    private Long id;
    private CityQuery city;
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    public static TripQuery of (Trip trip) {
        return TripQuery.builder()
                .id(trip.getId())
                .city(CityQuery.of(trip.getCity()))
                .startAt(trip.getStartAt())
                .endAt(trip.getEndAt())
                .build();
    }
}
