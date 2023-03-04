package com.flight.schedule.demo.service;

import com.flight.schedule.demo.entity.FlightEntity;
import com.flight.schedule.demo.mapper.FlightMapper;
import com.flight.schedule.demo.model.FlightEntryRequest;
import com.flight.schedule.demo.model.FlightSearchRequest;
import com.flight.schedule.demo.model.FlightSearchResponse;
import com.flight.schedule.demo.model.FlightSearchSort;
import com.flight.schedule.demo.repository.FlightRepository;
import com.flight.schedule.demo.util.FlightUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class FlightService {

    private FlightRepository flightRepository;

    private FlightMapper flightMapper;

    public void createFlightEntry(List<FlightEntryRequest> flightEntryRequests){
        List<FlightEntity> flightEntityList = flightMapper.toFlightEntity(flightEntryRequests);
        flightRepository.saveAll(flightEntityList);
    }


    public List<FlightSearchResponse> searchFlight(FlightSearchRequest flightSearchRequest){

        List<FlightEntity> allFlightsFromSource = flightRepository.findAllByDepartureAirportAndFlightDate(flightSearchRequest.getDepartureAirport(), flightSearchRequest.getTravelDate());
        List<String> arrivalAirports= allFlightsFromSource.stream().map(FlightEntity::getArrivalAirport).toList();
        Optional<FlightEntity> flightEntity = allFlightsFromSource.stream().min(Comparator.comparing(FlightEntity::getArrivalTime));
        OffsetTime minArrivalTime=flightEntity.get().getArrivalTime();
        log.info("minimum arrival time: {}",minArrivalTime);
        List<FlightEntity> allConnectingFlights = flightRepository.findAllByArrivalAirportAndFlightDateAndDepartureTimeAfterAndDepartureAirportIn(flightSearchRequest.getArrivalAirport(), flightSearchRequest.getTravelDate(),minArrivalTime,arrivalAirports);
        log.info("allConnectingFlights: {}",allConnectingFlights);
        List<FlightSearchResponse> response= mapFlights(allFlightsFromSource,allConnectingFlights);
        if(flightSearchRequest.getSortBy().equals(FlightSearchSort.EARLY_DEPT)){
           return response.stream().sorted(Comparator.comparing(FlightSearchResponse::getDepartureTime)).toList();
        }else {
           return response.stream().sorted(Comparator.comparing(FlightSearchResponse::getArrivalTime)).toList();
        }
    }

    public List<FlightSearchResponse> mapFlights(List<FlightEntity> allFlightsFromSource,List<FlightEntity> allConnectingFlights){
        List<FlightSearchResponse> flightSearchResponses=new ArrayList<>();
        allFlightsFromSource.forEach(flightEntity -> {

            List<FlightEntity> connectingFlights = findConnectingFlight(allConnectingFlights, flightEntity.getArrivalAirport(), flightEntity.getArrivalTime());
            addResponse(flightSearchResponses,flightEntity,connectingFlights);

        });
        return flightSearchResponses;
    }

    private List<FlightEntity> findConnectingFlight(List<FlightEntity> allConnectingFlights,String deptAirport,OffsetTime departureTimeAfter){
        List<FlightEntity> flightEntityList = allConnectingFlights.stream().sorted(Comparator.comparing(FlightEntity::getDepartureTime))
                .filter(flightEntity -> flightEntity.getDepartureAirport().equals(deptAirport) && flightEntity.getDepartureTime().isAfter(departureTimeAfter))
                .toList();
        return flightEntityList;
    }

    private void addResponse(List<FlightSearchResponse> flightSearchResponseList,FlightEntity sourceFlight,List<FlightEntity> connectingFlights){
        List<FlightSearchResponse> flightSearchResponses = connectingFlights.stream().map(connectingFlight -> toFlightSearchResponse(sourceFlight, connectingFlight)).toList();
        flightSearchResponseList.addAll(flightSearchResponses);

    }

    private FlightSearchResponse toFlightSearchResponse(FlightEntity sourceFlight,FlightEntity connectingFlight){
        Duration duration = Duration.between(sourceFlight.getArrivalTime(), connectingFlight.getDepartureTime());
        return FlightSearchResponse.builder()
                .departureAirport(sourceFlight.getDepartureAirport())
                .arrivalAirport(sourceFlight.getArrivalAirport())
                .arrivalTime(sourceFlight.getArrivalTime())
                .flightNo(sourceFlight.getFlightNumber())
                .flightDate(sourceFlight.getFlightDate())
                .departureTime(sourceFlight.getDepartureTime())
                .connectionFlightNo(connectingFlight.getFlightNumber())
                .connectionArrivalAirport(connectingFlight.getArrivalAirport())
                .connectionFlightDate(connectingFlight.getFlightDate())
                .connectionDepartureAirport(connectingFlight.getDepartureAirport())
                .connectionFlightDepartureTime(connectingFlight.getDepartureTime())
                .connectionFlightArrivalTime(connectingFlight.getArrivalTime())
                .connectionTime(String.format("%02d:%02d", duration.toHoursPart(), duration.toMinutesPart()))
                .build();
    }





}
