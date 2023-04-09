package com.triple.task.travel.trip.adapter.out.persistence;

import com.triple.task.travel.trip.domain.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TripRepository extends JpaRepository<Trip, Long> {
    @Query("select t from Trip t join fetch t.city where t.id = :tripId")
    Optional<Trip> selectTripWithCityBy(Long tripId);
}
