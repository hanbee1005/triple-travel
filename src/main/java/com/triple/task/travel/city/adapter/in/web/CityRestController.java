package com.triple.task.travel.city.adapter.in.web;

import com.triple.task.travel.city.adapter.in.web.model.CreateAndUpdateCityRequest;
import com.triple.task.travel.city.application.model.CityQuery;
import com.triple.task.travel.city.application.model.CityQueryList;
import com.triple.task.travel.city.application.service.CityCommandService;
import com.triple.task.travel.city.application.service.CityQueryService;
import com.triple.task.travel.common.model.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CityRestController {
    private final CityCommandService cityCommandService;
    private final CityQueryService cityQueryService;

    @GetMapping("/city")
    public CommonResponse<CityQueryList> findCities() {
        return CommonResponse.ok(cityQueryService.selectAll());
    }

    @GetMapping("/city/{cityId}")
    public CommonResponse<CityQuery> findCity(@PathVariable Long cityId) {
        CityQuery selectOne = cityQueryService.selectOne(cityId);
         cityCommandService.addCityViewCount(cityId);
        return CommonResponse.ok(selectOne);
    }

    @GetMapping("/city/{cityId}/member/{memberId}")
    public void findCities(@PathVariable Long cityId,
                           @PathVariable Long memberId) {

    }

    @PostMapping("/city")
    public CommonResponse<CityQuery> createCity(@RequestBody @Valid CreateAndUpdateCityRequest request) {
        return CommonResponse.ok(cityCommandService.save(request.toCreateCityCommand()));
    }

    @PatchMapping("/city/{cityId}")
    public CommonResponse<CityQuery> updateCity(@PathVariable Long cityId,
                                                @RequestBody @Valid CreateAndUpdateCityRequest request) {
        return CommonResponse.ok(cityCommandService.update(request.toUpdateCityCommand(cityId)));
    }

    @DeleteMapping("/city/{cityId}")
    public CommonResponse deleteCity(@PathVariable Long cityId) {
        cityCommandService.delete(cityId);
        return CommonResponse.ok();
    }
}
