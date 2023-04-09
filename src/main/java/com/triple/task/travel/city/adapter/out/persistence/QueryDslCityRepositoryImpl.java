package com.triple.task.travel.city.adapter.out.persistence;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.triple.task.travel.city.domain.City;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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
                .leftJoin(city.cityViews, cityView)
                .where(ongoingAndScheduledTrip(memberId)
                        .or(isCreatedWithinADay())
                        .or(isSearchedOneMoreTimeWithinAWeek()))
                .orderBy(trip.startAt.asc(), city.createdAt.desc(), cityView.createdAt.desc())
                .fetch();
    }

    @Override
    public List<City> ongoingTripCities(Long memberId) {
        return jpaQueryFactory
                .selectFrom(city)
                .join(city.trips, trip)
                .where(trip.creator.eq(memberId)
                        .and(trip.startAt.before(LocalDateTime.now()))
                        .and(trip.endAt.after(LocalDateTime.now())))
                .orderBy(trip.startAt.asc())
                .fetch();
    }

    @Override
    public List<City> scheduledTripCities(Long memberId) {
        return jpaQueryFactory
                .selectFrom(city)
                .join(city.trips, trip)
                .where(trip.creator.eq(memberId)
                        .and(trip.startAt.after(LocalDateTime.now())))
                .orderBy(trip.startAt.asc())
                .fetch();
    }

    @Override
    public List<City> createdWithinADay() {
        return jpaQueryFactory
                .selectFrom(city)
                .where(city.createdAt.after(LocalDateTime.of(LocalDate.now().minusDays(2), LocalTime.of(23, 59, 59))))
                .orderBy(city.createdAt.asc())
                .fetch();
    }

    @Override
    public List<City> searchedOneMoreTimeWithinAWeek() {
        return jpaQueryFactory
                .selectFrom(city)
                .leftJoin(city.cityViews, cityView)
                .where(cityView.createdAt.after(LocalDateTime.of(LocalDate.now().minusWeeks(1).minusDays(1), LocalTime.of(23, 59, 59))))
                .orderBy(cityView.createdAt.asc())
                .fetch();
    }

    private BooleanExpression eqTripCreator(Long memberId) {
        return trip.creator.eq(memberId);
    }

    private BooleanExpression ongoingAndScheduledTrip(Long memberId) {
        return trip.creator.eq(memberId).and(trip.endAt.after(LocalDateTime.now()));
    }

    private BooleanExpression ongoingTrip(Long memberId) {
        return trip.creator.eq(memberId)
                .and(trip.startAt.before(LocalDateTime.now()))
                .and(trip.endAt.after(LocalDateTime.now()));
    }

    private BooleanExpression scheduledTrip(Long memberId) {
        return trip.creator.eq(memberId)
                .and(trip.startAt.after(LocalDateTime.now()));
    }

    private BooleanExpression isCreatedWithinADay() {
        return city.createdAt.after(LocalDateTime.of(LocalDate.now().minusDays(2), LocalTime.of(23, 59, 59)));
    }

    private BooleanExpression isSearchedOneMoreTimeWithinAWeek() {
        return cityView.createdAt.after(LocalDateTime.of(LocalDate.now().minusWeeks(1).minusDays(1), LocalTime.of(23, 59, 59)));
    }
}
