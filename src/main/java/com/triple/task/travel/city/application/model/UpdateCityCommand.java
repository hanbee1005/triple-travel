package com.triple.task.travel.city.application.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateCityCommand {
    private Long cityId;
    private String name;
    private String continent;
    private String country;
    private Long memberId;
}
