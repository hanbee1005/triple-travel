package com.triple.task.travel.city.adapter.out.persistence;

import com.triple.task.travel.city.domain.CityView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CityViewRepository extends JpaRepository<CityView, Long> {
    @Modifying
    @Transactional
    @Query("delete from CityView cv where cv.city.id = :cityId")
    int deleteCityViewsBy(Long cityId);
}
