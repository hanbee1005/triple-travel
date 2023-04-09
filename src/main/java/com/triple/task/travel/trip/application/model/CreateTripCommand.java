package com.triple.task.travel.trip.application.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CreateTripCommand {
    private Long cityId;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Long memberId;
}
