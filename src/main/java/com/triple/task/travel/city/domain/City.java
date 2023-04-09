package com.triple.task.travel.city.domain;

import com.triple.task.travel.city.application.model.CreateCityCommand;
import com.triple.task.travel.city.application.model.UpdateCityCommand;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@DynamicUpdate
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String continent;
    private String country;

    private Long creator;
    private Long lastModifier;

    public static City create(CreateCityCommand command) {
        return City.builder()
                .name(command.getName())
                .continent(command.getContinent())
                .country(command.getCountry())
                .creator(command.getMemberId())
                .lastModifier(command.getMemberId())
                .build();
    }

    public void edit(UpdateCityCommand command) {
        this.name = command.getName();
        this.continent = command.getContinent();
        this.country = command.getCountry();
        this.lastModifier = command.getMemberId();
    }
}