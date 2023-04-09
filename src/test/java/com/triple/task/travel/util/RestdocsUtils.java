package com.triple.task.travel.util;

import org.springframework.http.MediaType;
import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.restdocs.snippet.Attributes;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

public class RestdocsUtils {
    private static final String SCHEME = "http";
    private static final String HOST = "localhost";

    public static OperationRequestPreprocessor getDocumentRequest() {
        return preprocessRequest(
                modifyUris()
                        .scheme(SCHEME)
                        .host(HOST)
                        .removePort(),
                prettyPrint());
    }

    public static OperationResponsePreprocessor getDocumentResponse() {
        return preprocessResponse(prettyPrint());
    }

    public static ResponseFieldsSnippet commonResponseFields(boolean isCollection, FieldDescriptor... descriptors) {
        return PayloadDocumentation.responseFields(
                fieldWithPath("code").type(JsonFieldType.NUMBER).description("응답 코드"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메시지")).and(
                Arrays.stream(descriptors)
                        .map(fd -> {
                            if (fd.isOptional()) {
                                return fieldWithPath((isCollection ? "data[]." : "data.") + fd.getPath()).type(fd.getType()).optional().description(fd.getDescription());
                            } else {
                                return fieldWithPath((isCollection ? "data[]." : "data.") + fd.getPath()).type(fd.getType()).description(fd.getDescription());
                            }
                        })
                        .collect(Collectors.toList()));
    }

    public static MockHttpServletRequestBuilder postWrapper(String url, String requestString) {
        MockHttpServletRequestBuilder builder = post(url)
                .content(requestString)
                .contentType(MediaType.APPLICATION_JSON);

        return setCustomHeader(url, builder);
    }

    public static MockHttpServletRequestBuilder patchWrapper(String url, String pathVariable, String requestString) {
        MockHttpServletRequestBuilder builder;
        if (StringUtils.hasText(requestString)) {
            builder = patch(url, pathVariable)
                    .content(requestString)
                    .contentType(MediaType.APPLICATION_JSON);
        } else {
            builder = patch(url, pathVariable)
                    .contentType(MediaType.APPLICATION_JSON);
        }

        return setCustomHeader(url, builder);
    }

    public static MockHttpServletRequestBuilder deleteWrapper(String url, String pathVariable) {
        MockHttpServletRequestBuilder builder = delete(url, pathVariable)
                .contentType(MediaType.APPLICATION_JSON);

        return setCustomHeader(url, builder);
    }

    public static MockHttpServletRequestBuilder getWrapper(String url) {
        MockHttpServletRequestBuilder builder = get(url);
        return setCustomHeader(url, builder);
    }

    public static MockHttpServletRequestBuilder getWrapper(String url, String pathVariable) {
        MockHttpServletRequestBuilder builder = get(url, pathVariable);

        return setCustomHeader(url, builder);
    }

    public static MockHttpServletRequestBuilder getWrapper(String url, Object... pathVariables) {
        MockHttpServletRequestBuilder builder = get(url, pathVariables);
        return setCustomHeader(url, builder);
    }

    private static MockHttpServletRequestBuilder setCustomHeader(String url, MockHttpServletRequestBuilder builder) {
        return builder.header("Endpoint", SCHEME + "://" + HOST);
    }

    private static ParameterDescriptor setType(ParameterDescriptor parameterDescriptor, String type) {
        parameterDescriptor.attributes(new Attributes.Attribute("type", type));
        return parameterDescriptor;
    }

    public static ParameterDescriptor parameterWithNameAndType(@NotNull String name, @NotNull String type) {
        return setType(parameterWithName(name), type);
    }
}