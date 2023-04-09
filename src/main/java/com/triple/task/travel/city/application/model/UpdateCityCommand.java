package com.triple.task.travel.city.application.model;

import lombok.Getter;

@Getter
public class UpdateCityCommand {
    private Long cityId;
    private String name;
    private String continent;
    private String country;
    private Long memberId;
}
