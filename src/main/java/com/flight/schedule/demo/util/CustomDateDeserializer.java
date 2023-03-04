package com.flight.schedule.demo.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class CustomDateDeserializer extends JsonDeserializer<OffsetDateTime> {

    private static final String PATTERN = "dd-MMM-yyyy";

    private final DateTimeFormatter formatter;

    public CustomDateDeserializer() {
        this.formatter = DateTimeFormatter.ofPattern(PATTERN);
    }

    @Override
    public OffsetDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException, JacksonException {
        LocalDate localDate = LocalDate.parse(parser.getText(), formatter);
        return OffsetDateTime.of(localDate,LocalTime.NOON, ZoneOffset.systemDefault().getRules().getOffset(Instant.now()));

    }
}