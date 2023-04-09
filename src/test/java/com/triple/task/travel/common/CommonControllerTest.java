package com.triple.task.travel.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@ExtendWith(RestDocumentationExtension.class)
public abstract class CommonControllerTest {
    protected static final String API_V1 = "/v1";

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;

    @BeforeEach
    protected void setup(WebApplicationContext context, RestDocumentationContextProvider provider) {
        this.mockMvc= MockMvcBuilders.webAppContextSetup(context)
                .apply(MockMvcRestDocumentation.documentationConfiguration(provider))
                .addFilters(new CharacterEncodingFilter("UTF-8",true))
                .build();
    }

    @AfterEach
    protected void finish() throws Exception {
    }
}
