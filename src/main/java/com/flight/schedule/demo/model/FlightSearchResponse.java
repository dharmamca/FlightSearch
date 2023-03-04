package com.flight.schedule.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import java.time.OffsetTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightSearchResponse {

    private String flightNo;
    @DateTimeFormat(pattern = "dd-MMM-yyyy")
    @JsonFormat(pattern = "dd-MMM-yyyy")
    private OffsetDateTime flightDate;
    private String departureAirport;
    private String arrivalAirport;
    @DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(pattern = "HH:mm")
    private OffsetTime departureTime;
    @DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(pattern = "HH:mm")
    private OffsetTime arrivalTime;
    private String connectionFlightNo;
    @DateTimeFormat(pattern = "dd-MMM-yyyy")
    @JsonFormat(pattern = "dd-MMM-yyyy")
    private OffsetDateTime connectionFlightDate;
    private String connectionDepartureAirport;
    private String connectionArrivalAirport;
    @DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(pattern = "HH:mm")
    private OffsetTime connectionFlightDepartureTime;
    @DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(pattern = "HH:mm")
    private OffsetTime connectionFlightArrivalTime;
    private String connectionTime;
}
