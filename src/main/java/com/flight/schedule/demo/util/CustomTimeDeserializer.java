package com.flight.schedule.demo.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class CustomTimeDeserializer extends JsonDeserializer<OffsetTime> {

    private static final String PATTERN = "HH:mm";

    private final DateTimeFormatter formatter;

    public CustomTimeDeserializer() {
        this.formatter = DateTimeFormatter.ofPattern(PATTERN);
    }

    @Override
    public OffsetTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        LocalTime localTime = LocalTime.parse(parser.getText(), formatter);
        return OffsetTime.of(localTime, ZoneOffset.systemDefault().getRules().getOffset(Instant.now()));
    }
}