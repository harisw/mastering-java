package org.harisw.expensetracker.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JacksonConfig {
    private static final ObjectMapper OBJECT_MAPPER = create();

    private JacksonConfig() {

    }

    private static ObjectMapper create() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }

    public static ObjectMapper objectMapper() {
        return OBJECT_MAPPER;
    }
}
