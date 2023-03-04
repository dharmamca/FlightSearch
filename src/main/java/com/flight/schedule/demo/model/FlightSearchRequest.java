package com.flight.schedule.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.flight.schedule.demo.util.CustomDateDeserializer;
import com.flight.schedule.demo.util.FlightUtil;
import lombok.Data;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;

@Data
public class FlightSearchRequest {

    private String departureAirport;

    private String arrivalAirport;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    private OffsetDateTime travelDate;

    private FlightSearchSort sortBy;




}
