package com.triple.task.travel.trip.application.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UpdateTripCommand {
    private Long tripId;
    private Long cityId;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Long memberId;
}
