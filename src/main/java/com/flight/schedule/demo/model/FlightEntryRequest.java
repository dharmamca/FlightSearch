package com.flight.schedule.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.flight.schedule.demo.util.CustomDateDeserializer;
import com.flight.schedule.demo.util.CustomTimeDeserializer;
import com.flight.schedule.demo.util.FlightUtil;
import lombok.Data;

import java.time.*;
import java.time.format.DateTimeFormatter;

@Data
public class FlightEntryRequest {

    private String flightNo;


    @JsonDeserialize(using = CustomDateDeserializer.class)
    private OffsetDateTime flightDate;

    private String departureAirport;

    private String arrivalAirport;

    @JsonDeserialize(using = CustomTimeDeserializer.class)
    private OffsetTime departureTime;

    @JsonDeserialize(using = CustomTimeDeserializer.class)
    private OffsetTime arrivalTime;




}
