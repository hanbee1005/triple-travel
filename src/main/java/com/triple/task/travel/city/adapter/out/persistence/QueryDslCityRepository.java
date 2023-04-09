package com.triple.task.travel.city.adapter.out.persistence;

import com.triple.task.travel.city.domain.City;

import java.util.List;

public interface QueryDslCityRepository {
    List<City> findCitiesByUser(Long memberId);
    List<City> ongoingTripCities(Long memberId);
    List<City> scheduledTripCities(Long memberId);
    List<City> createdWithinADay();
    List<City> searchedOneMoreTimeWithinAWeek();
}
