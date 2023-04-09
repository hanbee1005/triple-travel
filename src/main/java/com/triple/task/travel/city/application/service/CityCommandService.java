package com.triple.task.travel.city.application.service;

import com.triple.task.travel.city.adapter.out.persistence.CityRepository;
import com.triple.task.travel.city.application.model.CityQuery;
import com.triple.task.travel.city.application.model.CreateCityCommand;
import com.triple.task.travel.city.application.model.UpdateCityCommand;
import com.triple.task.travel.city.domain.City;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CityCommandService {
    private final CityRepository cityRepository;
    private final CityQueryService cityQueryService;

    public CityQuery save(CreateCityCommand command) {
        City city = cityRepository.save(City.create(command));
        return makeCityQueryBy(city);
    }

    public CityQuery update(UpdateCityCommand command) {
        City city = cityQueryService.selectBy(command.getCityId());
        city.edit(command);
        return makeCityQueryBy(city);
    }

    public void delete(Long cityId) {
        // TODO 여행이 지정되지 않은 경우만 삭제
        cityRepository.deleteById(cityId);
    }

    private CityQuery makeCityQueryBy(City city) {
        return CityQuery.builder()
                .id(city.getId())
                .name(city.getName())
                .continent(city.getContinent())
                .country(city.getCountry())
                .build();
    }
}
