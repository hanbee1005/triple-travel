package com.triple.task.travel.city.adapter.out.persistence;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.triple.task.travel.city.domain.City;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static com.triple.task.travel.city.domain.QCity.city;
import static com.triple.task.travel.city.domain.QCityView.cityView;
import static com.triple.task.travel.trip.domain.QTrip.trip;

@RequiredArgsConstructor
public class QueryDslCityRepositoryImpl implements QueryDslCityRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<City> findCitiesByUser(Long memberId) {
        return jpaQueryFactory
                .selectFrom(city)
                .leftJoin(city.trips, trip)
                .on(ongoingAndScheduledTrip(memberId))
                .leftJoin(city.cityViews, cityView)
                .on(isSearchedOneMoreTimeWithinAWeek())
                .orderBy(trip.startAt.asc().nullsLast(), isCreatedWithinADay().desc(), cityView.createdAt.desc().nullsLast())
                .fetch();
    }

    @Override
    public Optional<City> findCityWithTrip(Long cityId) {
        return jpaQueryFactory
                .selectFrom(city)
                .leftJoin(city.trips, trip)
                .fetchJoin()
                .where(city.id.eq(cityId))
                .stream().findAny();
    }

    private BooleanExpression ongoingAndScheduledTrip(Long memberId) {
        return trip.creator.eq(memberId).and(trip.endAt.goe(LocalDateTime.now()));
    }

    private NumberExpression isCreatedWithinADay() {
        return new CaseBuilder().when(city.createdAt.goe(LocalDateTime.of(LocalDate.now().minusDays(2), LocalTime.of(23, 59, 59))))
                .then(1)
                .otherwise(0);
    }

    private BooleanExpression isSearchedOneMoreTimeWithinAWeek() {
        return cityView.createdAt.goe(LocalDateTime.of(LocalDate.now().minusWeeks(1).minusDays(1), LocalTime.of(23, 59, 59)));
    }
}
