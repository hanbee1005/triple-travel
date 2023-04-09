package com.triple.task.travel.city.application.model;

import lombok.Getter;

@Getter
public class CreateCityCommand {
    private String name;
    private String continent;
    private String country;
    private Long memberId;
}
