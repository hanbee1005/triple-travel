package com.triple.task.travel.trip.application.service;

import com.triple.task.travel.city.application.service.CityQueryService;
import com.triple.task.travel.city.domain.City;
import com.triple.task.travel.trip.adapter.out.persistence.TripRepository;
import com.triple.task.travel.trip.application.model.CreateTripCommand;
import com.triple.task.travel.trip.application.model.TripQuery;
import com.triple.task.travel.trip.application.model.UpdateTripCommand;
import com.triple.task.travel.trip.domain.Trip;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TripCommandService {
    private final TripRepository tripRepository;
    private final TripQueryService tripQueryService;
    private final CityQueryService cityQueryService;

    public TripQuery save(CreateTripCommand command) {
        Trip trip = tripRepository.save(Trip.create(command, cityQueryService.selectBy(command.getCityId())));
        return TripQuery.of(trip);
    }

    public TripQuery update(UpdateTripCommand command) {
        Trip trip = tripQueryService.selectTripWithCityBy(command.getTripId());
        trip.edit(command);

        if (!Objects.equals(trip.getCity().getId(), command.getCityId())) {
            City city = cityQueryService.selectBy(command.getCityId());
            trip.editCity(city);
        }

        return TripQuery.of(trip);
    }

    public void delete(Long tripId) {
        tripRepository.deleteById(tripId);
    }
}
