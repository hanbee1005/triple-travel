package com.triple.task.travel.city.adapter.out.persistence;

import com.triple.task.travel.city.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
