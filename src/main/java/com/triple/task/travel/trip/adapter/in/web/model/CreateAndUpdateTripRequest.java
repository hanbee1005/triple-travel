package com.triple.task.travel.trip.adapter.in.web.model;

import com.triple.task.travel.trip.application.model.CreateTripCommand;
import com.triple.task.travel.trip.application.model.UpdateTripCommand;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Builder
public class CreateAndUpdateTripRequest {
    @NotNull
    private Long cityId;
    @NotNull
    private LocalDateTime startAt;
    @NotNull
    private LocalDateTime endAt;
    @NotNull
    private Long memberId;

    public CreateTripCommand toCreateTripCommand() {
        return CreateTripCommand.builder()
                .cityId(cityId)
                .startAt(startAt)
                .endAt(endAt)
                .memberId(memberId)
                .build();
    }

    public UpdateTripCommand toUpdateTripCommand(Long tripId) {
        return UpdateTripCommand.builder()
                .tripId(tripId)
                .cityId(cityId)
                .startAt(startAt)
                .endAt(endAt)
                .memberId(memberId)
                .build();
    }

    public void validateTripDate() {
        if (!(startAt.isBefore(endAt) && endAt.isAfter(LocalDateTime.now()))) {
            throw new IllegalArgumentException();
        }
    }
}
