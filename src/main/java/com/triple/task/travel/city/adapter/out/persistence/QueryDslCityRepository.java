package com.triple.task.travel.city.adapter.out.persistence;

import com.triple.task.travel.city.domain.City;

import java.util.List;
import java.util.Optional;

public interface QueryDslCityRepository {
    List<City> findCitiesByUser(Long memberId);
    Optional<City> findCityWithTrip(Long cityId);
}
