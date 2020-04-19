package tr.kartal.airportticketapi.controller;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import tr.kartal.airportticketapi.exceptions.FlightNotFoundException;
import tr.kartal.airportticketapi.model.AirlineCompany;
import tr.kartal.airportticketapi.model.Flight;
import tr.kartal.airportticketapi.repository.FlightRepository;
import tr.kartal.airportticketapi.repository.rsql.CustomRsqlVisitor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1")
@Log4j2
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @RequestMapping(value = "flights", method = RequestMethod.GET)
    public List<Flight> list() {

        // şimdiki zamandan önce olan uçuşları engellemek için yazıldı.
        return flightRepository.findAll()
                .stream()
                .filter(x -> x.getDepartureDate().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "flights/add", method = RequestMethod.POST)
    public void create(@RequestBody Flight flight) throws Exception {
        if (flight.getDepartureDate().isAfter(LocalDateTime.now())) {
            flightRepository.save(flight);
        }else{
            throw new Exception("ArrivalDate must be after now!!");
        }
    }

    @RequestMapping(value = "flights/company", method = RequestMethod.GET)
    List<Flight> getFlightsWithCompany(@RequestBody AirlineCompany company) {
        return flightRepository.findByAirlineCompany(company)
                .stream()
                .filter(x -> x.getDepartureDate().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "flights/{id}", method = RequestMethod.GET)
    Flight get(@PathVariable Integer id) {
        return flightRepository.findById(id)
                .filter(x -> x.getDepartureDate().isAfter(LocalDateTime.now()))
                .orElseThrow(() -> new FlightNotFoundException(id));
    }

    @RequestMapping(value = "flight", method = RequestMethod.GET)
    @ResponseBody
    public List<Flight> findAllByRsql(@RequestParam(value = "search") String search) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<Flight> spec = rootNode.accept(new CustomRsqlVisitor<>());
        return flightRepository.findAll(spec)
                .stream()
                .filter(x -> x.getDepartureDate().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());

    }
}
