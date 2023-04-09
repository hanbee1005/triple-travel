package com.triple.task.travel.city.domain;

import com.triple.task.travel.common.model.AbstractCreatedAtEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class CityView extends AbstractCreatedAtEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cityId")
    private City city;

    public static CityView create(City city) {
        return CityView.builder()
                .city(city)
                .build();
    }
}
