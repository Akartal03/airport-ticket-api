package tr.kartal.airportticketapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import tr.kartal.airportticketapi.model.AirlineCompany;
import tr.kartal.airportticketapi.model.Flight;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer>, JpaSpecificationExecutor<Flight> {

    List<Flight> findByAirlineCompany(AirlineCompany company);
}
