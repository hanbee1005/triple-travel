package com.triple.task.travel.city.application.service;

import com.triple.task.travel.city.adapter.out.persistence.CityRepository;
import com.triple.task.travel.city.application.model.CityQuery;
import com.triple.task.travel.city.application.model.CityQueryList;
import com.triple.task.travel.city.domain.City;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CityQueryService {
    private final CityRepository cityRepository;

    public CityQueryList selectAll() {
        return CityQueryList.of(cityRepository.findAll().stream()
                .map(CityQuery::of)
                .collect(Collectors.toList()));
    }

    public CityQuery selectOne(Long cityId) {
        return CityQuery.of(selectBy(cityId));
    }

    public CityQueryList selectCitiesBy(Long memberId) {
        List<City> cities = cityRepository.findCitiesByUser(memberId);

        return CityQueryList.of(cities.stream()
                .map(CityQuery::of)
                .collect(Collectors.toList()));
    }

    public City selectBy(Long cityId) {
        // TODO 에러 처리 필요
        return cityRepository.findById(cityId).orElseThrow();
    }

    public City selectWithTripsBy(Long cityId) {
        // TODO 에러 처리 필요
        return cityRepository.findCityWithTrip(cityId).orElseThrow();
    }
}
