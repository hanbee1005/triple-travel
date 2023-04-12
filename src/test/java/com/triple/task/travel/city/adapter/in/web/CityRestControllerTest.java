package com.triple.task.travel.city.adapter.in.web;

import com.triple.task.travel.city.adapter.in.web.model.CreateAndUpdateCityRequest;
import com.triple.task.travel.city.application.model.CityQuery;
import com.triple.task.travel.city.application.model.CityQueryList;
import com.triple.task.travel.city.application.service.CityCommandService;
import com.triple.task.travel.city.application.service.CityQueryService;
import com.triple.task.travel.common.CommonControllerTest;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CityRestController.class)
class CityRestControllerTest extends CommonControllerTest {

    @MockBean
    private CityCommandService cityCommandService;
    @MockBean
    private CityQueryService cityQueryService;

    @Test
    @DisplayName("도시 등록")
    public void save() throws Exception {
        // given
        String documentPath = "city/create";
        String url = "/city";

        CreateAndUpdateCityRequest request = getCreateAndUpdateCityRequest();
        CityQuery response = getCityQuery();

        given(cityCommandService.save(any())).willReturn(response);

        // when
        ResultActions resultActions = mockMvc.perform(RestdocsUtils.postWrapper(url, objectMapper.writeValueAsString(request)));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document(documentPath,
                        RestdocsUtils.getDocumentRequest(),
                        RestdocsUtils.getDocumentResponse(),
                        requestFields(
                                PayloadDocumentation.fieldWithPath("name").type(JsonFieldType.STRING).description("도시명"),
                                PayloadDocumentation.fieldWithPath("continent").type(JsonFieldType.STRING).description("대륙명"),
                                PayloadDocumentation.fieldWithPath("country").type(JsonFieldType.STRING).description("나라명")
                        ),
                        RestdocsUtils.commonResponseFields(false,
                                PayloadDocumentation.fieldWithPath("id").type(JsonFieldType.NUMBER).description("도시 ID"),
                                PayloadDocumentation.fieldWithPath("name").type(JsonFieldType.STRING).description("도시명"),
                                PayloadDocumentation.fieldWithPath("continent").type(JsonFieldType.STRING).description("대륙명"),
                                PayloadDocumentation.fieldWithPath("country").type(JsonFieldType.STRING).description("나라명")
                        )
                )).andDo(print());
    }

    @Test
    @DisplayName("도시 수정")
    public void update() throws Exception {
        // given
        String documentPath = "city/update";
        String url = "/city/{cityId}";

        Long cityId = 1L;
        CreateAndUpdateCityRequest request = getCreateAndUpdateCityRequest();
        CityQuery response = getCityQuery();

        given(cityCommandService.update(any())).willReturn(response);

        // when
        ResultActions resultActions = mockMvc.perform(RestdocsUtils.patchWrapper(url, String.valueOf(cityId), objectMapper.writeValueAsString(request)));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document(documentPath,
                        RestdocsUtils.getDocumentRequest(),
                        RestdocsUtils.getDocumentResponse(),
                        RequestDocumentation.pathParameters(
                                RestdocsUtils.parameterWithNameAndType("cityId", "number").description("도시 ID")
                        ),
                        requestFields(
                                PayloadDocumentation.fieldWithPath("name").type(JsonFieldType.STRING).description("도시명"),
                                PayloadDocumentation.fieldWithPath("continent").type(JsonFieldType.STRING).description("대륙명"),
                                PayloadDocumentation.fieldWithPath("country").type(JsonFieldType.STRING).description("나라명")
                        ),
                        RestdocsUtils.commonResponseFields(false,
                                PayloadDocumentation.fieldWithPath("id").type(JsonFieldType.NUMBER).description("도시 ID"),
                                PayloadDocumentation.fieldWithPath("name").type(JsonFieldType.STRING).description("도시명"),
                                PayloadDocumentation.fieldWithPath("continent").type(JsonFieldType.STRING).description("대륙명"),
                                PayloadDocumentation.fieldWithPath("country").type(JsonFieldType.STRING).description("나라명")
                        )
                )).andDo(print());
    }

    @Test
    @DisplayName("도시 삭제")
    public void delete() throws Exception {
        // given
        String documentPath = "city/delete";
        String url = "/city/{cityId}";

        Long cityId = 1L;

        // when
        ResultActions resultActions = mockMvc.perform(RestdocsUtils.deleteWrapper(url, String.valueOf(cityId)));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document(documentPath,
                        RestdocsUtils.getDocumentRequest(),
                        RestdocsUtils.getDocumentResponse(),
                        RequestDocumentation.pathParameters(
                                RestdocsUtils.parameterWithNameAndType("cityId", "number").description("도시 ID")
                        ),
                        PayloadDocumentation.responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER).description("응답 코드"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메시지"),
                                PayloadDocumentation.fieldWithPath("data").type(JsonFieldType.NULL).description("결과")
                        )
                )).andDo(print());
    }

    @Test
    @DisplayName("도시 단건 조회 (조회수 증가)")
    public void findBy() throws Exception {
        // given
        String documentPath = "city/findOne";
        String url = "/city/{cityId}";

        Long cityId = 1L;
        CityQuery response = getCityQuery();

        given(cityQueryService.selectOne(any())).willReturn(response);

        // when
        ResultActions resultActions = mockMvc.perform(RestdocsUtils.getWrapper(url, String.valueOf(cityId)));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document(documentPath,
                        RestdocsUtils.getDocumentRequest(),
                        RestdocsUtils.getDocumentResponse(),
                        RequestDocumentation.pathParameters(
                                RestdocsUtils.parameterWithNameAndType("cityId", "number").description("도시 ID")
                        ),
                        RestdocsUtils.commonResponseFields(false,
                                PayloadDocumentation.fieldWithPath("id").type(JsonFieldType.NUMBER).description("도시 ID"),
                                PayloadDocumentation.fieldWithPath("name").type(JsonFieldType.STRING).description("도시명"),
                                PayloadDocumentation.fieldWithPath("continent").type(JsonFieldType.STRING).description("대륙명"),
                                PayloadDocumentation.fieldWithPath("country").type(JsonFieldType.STRING).description("나라명")
                        )
                )).andDo(print());
    }

    @Test
    @DisplayName("사용자 별 도시 조회")
    public void findByUser() throws Exception {
        // given
        String documentPath = "city/findByUser";
        String url = "/city/member/{memberId}";

        Long memberId = 1L;
        CityQueryList response = getCityQueryList();

        given(cityQueryService.selectCitiesBy(any())).willReturn(response);

        // when
        ResultActions resultActions = mockMvc.perform(RestdocsUtils.getWrapper(url, String.valueOf(memberId)));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document(documentPath,
                        RestdocsUtils.getDocumentRequest(),
                        RestdocsUtils.getDocumentResponse(),
                        RequestDocumentation.pathParameters(
                                RestdocsUtils.parameterWithNameAndType("memberId", "number").description("사용자 ID")
                        ),
                        RestdocsUtils.commonResponseFields(false,
                                PayloadDocumentation.fieldWithPath("count").type(JsonFieldType.NUMBER).description("조회된 도시 수"),
                                PayloadDocumentation.fieldWithPath("cities").type(JsonFieldType.ARRAY).description("조회된 도시 목록"),
                                PayloadDocumentation.fieldWithPath("cities[].id").type(JsonFieldType.NUMBER).description("도시 ID"),
                                PayloadDocumentation.fieldWithPath("cities[].name").type(JsonFieldType.STRING).description("도시명"),
                                PayloadDocumentation.fieldWithPath("cities[].continent").type(JsonFieldType.STRING).description("대륙명"),
                                PayloadDocumentation.fieldWithPath("cities[].country").type(JsonFieldType.STRING).description("나라명")
                        )
                )).andDo(print());
    }

}