package com.triple.task.travel.city.adapter.in.web.model;

import com.triple.task.travel.city.application.model.CreateCityCommand;
import com.triple.task.travel.city.application.model.UpdateCityCommand;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class CreateAndUpdateCityRequest {
    @NotBlank
    private String name;
    private String continent;
    private String country;

    public CreateCityCommand toCreateCityCommand() {
        return CreateCityCommand.builder()
                .name(name)
                .continent(continent)
                .country(country)
                .build();
    }

    public UpdateCityCommand toUpdateCityCommand(Long cityId) {
        return UpdateCityCommand.builder()
                .cityId(cityId)
                .name(name)
                .continent(continent)
                .country(country)
                .build();
    }
}
