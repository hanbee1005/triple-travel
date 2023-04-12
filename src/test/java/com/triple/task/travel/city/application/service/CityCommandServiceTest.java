package com.triple.task.travel.city.application.service;

import com.triple.task.travel.city.adapter.out.persistence.CityRepository;
import com.triple.task.travel.city.application.model.CreateCityCommand;
import com.triple.task.travel.city.domain.City;
import com.triple.task.travel.common.exception.BusinessException;
import com.triple.task.travel.trip.adapter.out.persistence.TripRepository;
import com.triple.task.travel.trip.application.model.CreateTripCommand;
import com.triple.task.travel.trip.domain.Trip;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class CityCommandServiceTest {
    @Autowired EntityManager em;

    @Autowired CityCommandService cityCommandService;
    @Autowired CityRepository cityRepository;
    @Autowired TripRepository tripRepository;

    @Test
    @DisplayName("도시 삭제 - 여행이 없는 경우")
    public void deleteCityWithoutTrip() {
        // given
        City savedCity = cityRepository.save(
                City.create(CreateCityCommand.builder().name("서울").continent("아시아").country("대한민국").build()));

        // when
        cityCommandService.delete(savedCity.getId());

        Optional<City> findCity = cityRepository.findById(savedCity.getId());

        // then
        assertThat(findCity).isEmpty();
    }

    @Test
    @DisplayName("도시 삭제 - 여행이 있는 경우")
    public void deleteCityWithTrip() {
        // given
        Long memberId = 1L;

        City savedCity = cityRepository.save(
                City.create(CreateCityCommand.builder().name("서울").continent("아시아").country("대한민국").build()));

        tripRepository.save(
                Trip.create(CreateTripCommand.builder()
                                .cityId(savedCity.getId())
                                .startAt(LocalDateTime.now())
                                .endAt(LocalDateTime.now().plusDays(1))
                                .memberId(memberId)
                                .build()
                        , savedCity));

        em.flush();
        em.clear();

        // when
        // then
        assertThrows(BusinessException.class, () -> cityCommandService.delete(savedCity.getId()));
    }

}