package com.flight.schedule.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import java.time.OffsetTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "flight_schedule")
public class FlightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNumber;

    @DateTimeFormat(pattern = "dd-MMM-yyyy")
    @JsonFormat(pattern = "dd-MMM-yyyy")
    private OffsetDateTime flightDate;

    private String departureAirport;

    private String arrivalAirport;

    @DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(pattern = "HH:mm")
    private OffsetTime  departureTime;

    @DateTimeFormat(pattern = "HH:MM")
    @JsonFormat(pattern = "HH:MM")
    private OffsetTime arrivalTime;


}
