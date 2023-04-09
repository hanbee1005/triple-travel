package com.triple.task.travel.trip.application.service;

import com.triple.task.travel.trip.adapter.out.persistence.TripRepository;
import com.triple.task.travel.trip.application.model.TripQuery;
import com.triple.task.travel.trip.domain.Trip;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TripQueryService {
    private final TripRepository tripRepository;

    public TripQuery selectOne(Long tripId) {
        return TripQuery.of(selectBy(tripId));
    }

    public Trip selectBy(Long tripId) {
        return tripRepository.findById(tripId).orElseThrow();
    }

    public Trip selectTripWithCityBy(Long tripId) {
        return tripRepository.selectTripWithCityBy(tripId).orElseThrow();
    }
}
