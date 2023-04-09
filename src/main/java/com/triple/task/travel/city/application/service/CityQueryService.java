package com.triple.task.travel.city.application.service;

import com.triple.task.travel.city.adapter.out.persistence.CityRepository;
import com.triple.task.travel.city.domain.City;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CityQueryService {
    private final CityRepository cityRepository;

    public void selectOne(Long cityId) {
        // TODO 조회 수 증가
    }

    public void selectCitiesBy(Long memberId) {
        // TODO 여행 중인 도시, 여행 예정인 도시
        // TODO 하루 이내에 등록된 도시, 최근 일주일 이내에 한번 이상 조회된 도시
        // TODO 무작위
    }

    public City selectBy(Long cityId) {
        // TODO 에러 처리 필요
        return cityRepository.findById(cityId).orElseThrow();
    }
}
