package com.flight.schedule.demo.mapper;

import com.flight.schedule.demo.entity.FlightEntity;
import com.flight.schedule.demo.model.FlightEntryRequest;
import com.flight.schedule.demo.util.FlightUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlightMapper {

    public List<FlightEntity> toFlightEntity(List<FlightEntryRequest> flightEntryRequests){
        return flightEntryRequests.stream().map(this::toFlightEntity).collect(Collectors.toList());
    }

    public FlightEntity toFlightEntity(FlightEntryRequest flightEntryRequest){
        return FlightEntity.builder()
                .departureAirport(flightEntryRequest.getDepartureAirport())
                .arrivalAirport(flightEntryRequest.getArrivalAirport())
                .arrivalTime(flightEntryRequest.getArrivalTime())
                .flightDate(flightEntryRequest.getFlightDate())
                .flightNumber(flightEntryRequest.getFlightNo())
                .departureTime(flightEntryRequest.getDepartureTime())
                .build();
    }
}
