package tr.kartal.airportticketapi.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tr.kartal.airportticketapi.model.AirlineCompany;
import tr.kartal.airportticketapi.model.Flight;
import tr.kartal.airportticketapi.repository.FlightRepository;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public Flight selectFlight(Integer id) {
        Optional<Flight> flight = flightRepository.findById(id);
        return flight.orElse(null);
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public void createFlight(Flight flight) {
        try {
            flightRepository.save(flight);
            log.info("flight saved, name :" + flight.toString());
        } catch (Exception ex) {
            log.error("flight save error," + ex.getMessage());
        }
    }

    public List<Flight> getFlightsWithCompany(AirlineCompany company) {
        List<Flight> ls = flightRepository.findByAirlineCompany(company);
        return ls;
    }

    public List<Flight> findAll(Specification<Flight> spec) {
        return flightRepository.findAll(spec);
    }
}
