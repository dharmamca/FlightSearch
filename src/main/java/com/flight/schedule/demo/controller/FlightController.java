package com.flight.schedule.demo.controller;

import com.flight.schedule.demo.model.FlightEntryRequest;
import com.flight.schedule.demo.model.FlightSearchRequest;
import com.flight.schedule.demo.model.FlightSearchResponse;
import com.flight.schedule.demo.service.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flight")
@AllArgsConstructor
public class FlightController {

    private FlightService flightService;


    @PostMapping("/entry")
    public void makeFlightEntry(@RequestBody List<FlightEntryRequest> flightEntryRequests){
        flightService.createFlightEntry(flightEntryRequests);
    }

    @PostMapping("/search")
    public List<FlightSearchResponse> searchFlights(@RequestBody FlightSearchRequest flightSearchRequest){
        return flightService.searchFlight(flightSearchRequest);
    }
}
