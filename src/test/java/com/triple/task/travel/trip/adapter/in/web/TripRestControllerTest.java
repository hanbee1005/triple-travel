package com.triple.task.travel.trip.adapter.in.web;

import com.triple.task.travel.common.CommonControllerTest;
import com.triple.task.travel.trip.adapter.in.web.model.CreateAndUpdateTripRequest;
import com.triple.task.travel.trip.application.model.TripQuery;
import com.triple.task.travel.trip.application.service.TripCommandService;
import com.triple.task.travel.trip.application.service.TripQueryService;
import com.triple.task.travel.util.RestdocsUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.web.servlet.ResultActions;

import static com.triple.task.travel.mock.MockModel.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TripRestController.class)
class TripRestControllerTest extends CommonControllerTest {

    @MockBean
    private TripCommandService tripCommandService;
    @MockBean
    private TripQueryService tripQueryService;

    @Test
    @DisplayName("여행 등록")
    public void save() throws Exception {
        // given
        String documentPath = "trip/create";
        String url = "/trip";

        CreateAndUpdateTripRequest request = getCreateAndUpdateTripRequest();
        TripQuery response = getTripQuery();

        given(tripCommandService.save(any())).willReturn(response);

        // when
        ResultActions resultActions = mockMvc.perform(RestdocsUtils.postWrapper(url, objectMapper.writeValueAsString(request)));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document(documentPath,
                        RestdocsUtils.getDocumentRequest(),
                        RestdocsUtils.getDocumentResponse(),
                        requestFields(
                                PayloadDocumentation.fieldWithPath("cityId").type(JsonFieldType.NUMBER).description("도시 ID"),
                                PayloadDocumentation.fieldWithPath("startAt").type(JsonFieldType.STRING).description("여행 시작 일시"),
                                PayloadDocumentation.fieldWithPath("endAt").type(JsonFieldType.STRING).description("여행 종료 일시"),
                                PayloadDocumentation.fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("사용자 ID")
                        ),
                        RestdocsUtils.commonResponseFields(false,
                                PayloadDocumentation.fieldWithPath("id").type(JsonFieldType.NUMBER).description("여행 ID"),
                                PayloadDocumentation.fieldWithPath("city").type(JsonFieldType.OBJECT).description("도시"),
                                PayloadDocumentation.fieldWithPath("city.id").type(JsonFieldType.NUMBER).description("도시 ID"),
                                PayloadDocumentation.fieldWithPath("city.name").type(JsonFieldType.STRING).description("도시명"),
                                PayloadDocumentation.fieldWithPath("city.continent").type(JsonFieldType.STRING).description("대륙명"),
                                PayloadDocumentation.fieldWithPath("city.country").type(JsonFieldType.STRING).description("나라명"),
                                PayloadDocumentation.fieldWithPath("startAt").type(JsonFieldType.STRING).description("여행 시작 일시"),
                                PayloadDocumentation.fieldWithPath("endAt").type(JsonFieldType.STRING).description("여행 종료 일시")
                        )
                )).andDo(print());
    }

    @Test
    @DisplayName("여행 수정")
    public void update() throws Exception {
        // given
        String documentPath = "trip/update";
        String url = "/trip/{tripId}";

        Long tripId = 1L;
        CreateAndUpdateTripRequest request = getCreateAndUpdateTripRequest();
        TripQuery response = getTripQuery();

        given(tripCommandService.update(any())).willReturn(response);

        // when
        ResultActions resultActions = mockMvc.perform(RestdocsUtils.patchWrapper(url, String.valueOf(tripId), objectMapper.writeValueAsString(request)));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document(documentPath,
                        RestdocsUtils.getDocumentRequest(),
                        RestdocsUtils.getDocumentResponse(),
                        RequestDocumentation.pathParameters(
                                RestdocsUtils.parameterWithNameAndType("tripId", "number").description("여행 ID")
                        ),
                        requestFields(
                                PayloadDocumentation.fieldWithPath("cityId").type(JsonFieldType.NUMBER).description("도시 ID"),
                                PayloadDocumentation.fieldWithPath("startAt").type(JsonFieldType.STRING).description("여행 시작 일시"),
                                PayloadDocumentation.fieldWithPath("endAt").type(JsonFieldType.STRING).description("여행 종료 일시"),
                                PayloadDocumentation.fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("사용자 ID")
                        ),
                        RestdocsUtils.commonResponseFields(false,
                                PayloadDocumentation.fieldWithPath("id").type(JsonFieldType.NUMBER).description("여행 ID"),
                                PayloadDocumentation.fieldWithPath("city").type(JsonFieldType.OBJECT).description("도시"),
                                PayloadDocumentation.fieldWithPath("city.id").type(JsonFieldType.NUMBER).description("도시 ID"),
                                PayloadDocumentation.fieldWithPath("city.name").type(JsonFieldType.STRING).description("도시명"),
                                PayloadDocumentation.fieldWithPath("city.continent").type(JsonFieldType.STRING).description("대륙명"),
                                PayloadDocumentation.fieldWithPath("city.country").type(JsonFieldType.STRING).description("나라명"),
                                PayloadDocumentation.fieldWithPath("startAt").type(JsonFieldType.STRING).description("여행 시작 일시"),
                                PayloadDocumentation.fieldWithPath("endAt").type(JsonFieldType.STRING).description("여행 종료 일시")
                        )
                )).andDo(print());
    }

    @Test
    @DisplayName("여행 삭제")
    public void delete() throws Exception {
        // given
        String documentPath = "trip/delete";
        String url = "/trip/{tripId}";

        Long tripId = 1L;

        // when
        ResultActions resultActions = mockMvc.perform(RestdocsUtils.deleteWrapper(url, String.valueOf(tripId)));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document(documentPath,
                        RestdocsUtils.getDocumentRequest(),
                        RestdocsUtils.getDocumentResponse(),
                        RequestDocumentation.pathParameters(
                                RestdocsUtils.parameterWithNameAndType("tripId", "number").description("여행 ID")
                        ),
                        PayloadDocumentation.responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER).description("응답 코드"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메시지"),
                                PayloadDocumentation.fieldWithPath("data").type(JsonFieldType.NULL).description("결과")
                        )
                )).andDo(print());
    }

    @Test
    @DisplayName("여행 단건 조회")
    public void findBy() throws Exception {
        // given
        String documentPath = "trip/findOne";
        String url = "/trip/{tripId}";

        Long tripId = 1L;
        TripQuery response = getTripQuery();

        given(tripQueryService.selectOne(any())).willReturn(response);

        // when
        ResultActions resultActions = mockMvc.perform(RestdocsUtils.getWrapper(url, String.valueOf(tripId)));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document(documentPath,
                        RestdocsUtils.getDocumentRequest(),
                        RestdocsUtils.getDocumentResponse(),
                        RequestDocumentation.pathParameters(
                                RestdocsUtils.parameterWithNameAndType("tripId", "number").description("여행 ID")
                        ),
                        RestdocsUtils.commonResponseFields(false,
                                PayloadDocumentation.fieldWithPath("id").type(JsonFieldType.NUMBER).description("여행 ID"),
                                PayloadDocumentation.fieldWithPath("city").type(JsonFieldType.OBJECT).description("도시"),
                                PayloadDocumentation.fieldWithPath("city.id").type(JsonFieldType.NUMBER).description("도시 ID"),
                                PayloadDocumentation.fieldWithPath("city.name").type(JsonFieldType.STRING).description("도시명"),
                                PayloadDocumentation.fieldWithPath("city.continent").type(JsonFieldType.STRING).description("대륙명"),
                                PayloadDocumentation.fieldWithPath("city.country").type(JsonFieldType.STRING).description("나라명"),
                                PayloadDocumentation.fieldWithPath("startAt").type(JsonFieldType.STRING).description("여행 시작 일시"),
                                PayloadDocumentation.fieldWithPath("endAt").type(JsonFieldType.STRING).description("여행 종료 일시")
                        )
                )).andDo(print());
    }
}