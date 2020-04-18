package tr.kartal.airportticketapi.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tr.kartal.airportticketapi.model.Airport;
import tr.kartal.airportticketapi.repository.AirportRepository;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    public List<Airport> getAirports() {
        return airportRepository.findAll();
    }

    public void createAirport(Airport airport) {
        try {
            airportRepository.save(airport);
            log.info("airport saved, name :"+ airport.toString());
        }catch (Exception ex){
            log.error("airport save error,"+ ex.getMessage());
        }

    }

    public Airport selectAirport(Integer id) {
        Optional<Airport> airport = airportRepository.findById(id);
        return airport.orElse(null);
    }

    public Airport findByName(String name){
        Optional<Airport> airport = airportRepository.findByName(name);
        return airport.orElse(null);
    }

    public List<Airport> findAll(Specification<Airport> spec) {
        return airportRepository.findAll(spec);
    }
}


