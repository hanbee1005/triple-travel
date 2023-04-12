package com.triple.task.travel.trip.adapter.in.web;

import com.triple.task.travel.common.model.CommonResponse;
import com.triple.task.travel.trip.adapter.in.web.model.CreateAndUpdateTripRequest;
import com.triple.task.travel.trip.adapter.in.web.model.DeleteTripResponse;
import com.triple.task.travel.trip.application.model.TripQuery;
import com.triple.task.travel.trip.application.service.TripCommandService;
import com.triple.task.travel.trip.application.service.TripQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TripRestController {
    private final TripCommandService tripCommandService;
    private final TripQueryService tripQueryService;

    @GetMapping("/trip/{tripId}")
    public CommonResponse<TripQuery> findTrip(@PathVariable Long tripId) {
        return CommonResponse.ok(tripQueryService.selectOne(tripId));
    }

    @PostMapping("/trip")
    public CommonResponse<TripQuery> createTrip(@RequestBody CreateAndUpdateTripRequest request) {
        request.validateTripDate();
        return CommonResponse.ok(tripCommandService.save(request.toCreateTripCommand()));
    }

    @PatchMapping("/trip/{tripId}")
    public CommonResponse<TripQuery> updateTrip(@PathVariable Long tripId,
                                                @RequestBody CreateAndUpdateTripRequest request) {
        return CommonResponse.ok(tripCommandService.update(request.toUpdateTripCommand(tripId)));
    }

    @DeleteMapping("/trip/{tripId}")
    public CommonResponse<DeleteTripResponse> deleteTrip(@PathVariable Long tripId) {
        tripCommandService.delete(tripId);
        return CommonResponse.ok(DeleteTripResponse.builder().id(tripId).build());
    }
}
