package com.flight.schedule.demo.repository;

import com.flight.schedule.demo.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity,Long> {


    List<FlightEntity> findAllByDepartureAirportAndFlightDate(String departureAirport, OffsetDateTime flightDate);

   // @Query(value = "select * from FlightEntity where departureAirport IN (: previousFlightArrivalAirport) and arrivalAirport = :arrivalAirport and flightDate=:flightDate and departureTime > :minArrivalTime",nativeQuery = true)
    List<FlightEntity> findAllByArrivalAirportAndFlightDateAndDepartureTimeAfterAndDepartureAirportIn(String arrivalAirport, OffsetDateTime flightDate, OffsetTime departureTime,List<String> departureAirport);
}
