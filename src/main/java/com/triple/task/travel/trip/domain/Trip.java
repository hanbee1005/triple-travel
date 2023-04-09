package com.triple.task.travel.trip.domain;

import com.triple.task.travel.city.domain.City;
import com.triple.task.travel.common.model.AbstractDateTimeEntity;
import com.triple.task.travel.trip.application.model.CreateTripCommand;
import com.triple.task.travel.trip.application.model.UpdateTripCommand;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@DynamicUpdate
public class Trip extends AbstractDateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cityId")
    private City city;

    private LocalDateTime startAt;
    private LocalDateTime endAt;

    private Long creator;
    private Long lastModifier;

    public static Trip create(CreateTripCommand command, City city) {
        return Trip.builder()
                .city(city)
                .startAt(command.getStartAt())
                .endAt(command.getEndAt())
                .creator(command.getMemberId())
                .lastModifier(command.getMemberId())
                .build();
    }

    public void edit(UpdateTripCommand command) {
        this.startAt = command.getStartAt();
        this.endAt = command.getEndAt();
        this.lastModifier = command.getMemberId();
    }

    public void editCity(City city) {
        this.city = city;
    }
}
