package com.triple.task.travel.city.application.service;

import com.triple.task.travel.city.adapter.out.persistence.CityRepository;
import com.triple.task.travel.city.adapter.out.persistence.CityViewRepository;
import com.triple.task.travel.city.application.model.CityQuery;
import com.triple.task.travel.city.application.model.CreateCityCommand;
import com.triple.task.travel.city.application.model.UpdateCityCommand;
import com.triple.task.travel.city.domain.City;
import com.triple.task.travel.city.domain.CityView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CityCommandService {
    private final CityRepository cityRepository;
    private final CityViewRepository cityViewRepository;
    private final CityQueryService cityQueryService;

    public CityQuery save(CreateCityCommand command) {
        City city = cityRepository.save(City.create(command));
        return CityQuery.of(city);
    }

    public CityQuery update(UpdateCityCommand command) {
        City city = cityQueryService.selectBy(command.getCityId());
        city.edit(command);
        return CityQuery.of(city);
    }

    public void delete(Long cityId) {
        City city = cityQueryService.selectWithTripsBy(cityId);

        if (city.hasTrip()) {
            throw new RuntimeException();
        }

        cityViewRepository.deleteCityViewsBy(cityId);
        cityRepository.deleteById(cityId);
    }

    @Async
    public void addCityViewCount(Long cityId) {
        cityViewRepository.save(CityView.create(cityQueryService.selectBy(cityId)));
    }
}
