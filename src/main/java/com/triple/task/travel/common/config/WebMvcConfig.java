package com.triple.task.travel.common.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.triple.task.travel.common.constant.DateTimeFormatConstant.*;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            builder.simpleDateFormat(DEFAULT_DATE_FORMAT);
            builder.serializers(new LocalDateSerializer(yyyyMMdd));
            builder.serializers(new LocalDateTimeSerializer(yyyyMMddHHmmss));
            builder.deserializers(new LocalDateDeserializer(yyyyMMdd));
            builder.deserializers(new LocalDateTimeDeserializer(yyyyMMddHHmmss));
        };
    }
}
