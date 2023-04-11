package com.triple.task.travel.city.domain;

import com.triple.task.travel.city.application.model.CreateCityCommand;
import com.triple.task.travel.city.application.model.UpdateCityCommand;
import com.triple.task.travel.common.model.AbstractDateTimeEntity;
import com.triple.task.travel.trip.domain.Trip;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@DynamicUpdate
public class City extends AbstractDateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String continent;
    private String country;

    @Column(nullable = false)
    private Long creator;
    @Column(nullable = false)
    private Long lastModifier;

    @Builder.Default
    @OneToMany(mappedBy = "city")
    private List<CityView> cityViews = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "city")
    private List<Trip> trips = new ArrayList<>();

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
