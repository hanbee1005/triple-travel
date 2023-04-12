package com.triple.task.travel.mock;

import com.triple.task.travel.city.adapter.in.web.model.CreateAndUpdateCityRequest;
import com.triple.task.travel.city.application.model.CityQuery;
import com.triple.task.travel.city.application.model.CityQueryList;
import com.triple.task.travel.trip.adapter.in.web.model.CreateAndUpdateTripRequest;
import com.triple.task.travel.trip.application.model.TripQuery;

import java.time.LocalDateTime;
import java.util.List;

public class MockModel {
    public static CreateAndUpdateCityRequest getCreateAndUpdateCityRequest() {
        return CreateAndUpdateCityRequest.builder()
                .name("서울")
                .continent("아시아")
                .country("대한민국")
                .build();
    }

    public static CityQuery getCityQuery() {
        return CityQuery.builder()
                .id(1L)
                .name("서울")
                .continent("아시아")
                .country("대한민국")
                .build();
    }

    public static List<CityQuery> getCityQueries() {
        return List.of(
                CityQuery.builder()
                        .id(1L)
                        .name("서울")
                        .continent("아시아")
                        .country("대한민국")
                        .build(),
                CityQuery.builder()
                        .id(2L)
                        .name("뉴욕")
                        .continent("북아메리카")
                        .country("미국")
                        .build(),
                CityQuery.builder()
                        .id(3L)
                        .name("파리")
                        .continent("유럽")
                        .country("프랑스")
                        .build());
    }

    public static CityQueryList getCityQueryList() {
        return CityQueryList.builder()
                .count(getCityQueries().size())
                .cities(getCityQueries())
                .build();
    }

    public static CreateAndUpdateTripRequest getCreateAndUpdateTripRequest() {
        return CreateAndUpdateTripRequest.builder()
                .cityId(1L)
                .startAt(LocalDateTime.now())
                .endAt(LocalDateTime.now().plusDays(1))
                .memberId(1L)
                .build();
    }

    public static TripQuery getTripQuery() {
        return TripQuery.builder()
                .id(1L)
                .city(getCityQuery())
                .startAt(LocalDateTime.now())
                .endAt(LocalDateTime.now().plusDays(1))
                .build();
    }
}
