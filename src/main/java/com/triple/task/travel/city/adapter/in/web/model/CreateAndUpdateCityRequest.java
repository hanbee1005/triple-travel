package com.triple.task.travel.city.adapter.in.web.model;

import com.triple.task.travel.city.application.model.CreateCityCommand;
import com.triple.task.travel.city.application.model.UpdateCityCommand;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class CreateAndUpdateCityRequest {
    @NotBlank
    private String name;
    private String continent;
    private String country;
    @NotNull
    private Long memberId;

    public CreateCityCommand toCreateCityCommand() {
        return CreateCityCommand.builder()
                .name(name)
                .continent(continent)
                .country(country)
                .memberId(memberId)
                .build();
    }

    public UpdateCityCommand toUpdateCityCommand(Long cityId) {
        return UpdateCityCommand.builder()
                .cityId(cityId)
                .name(name)
                .continent(continent)
                .country(country)
                .memberId(memberId)
                .build();
    }
}
