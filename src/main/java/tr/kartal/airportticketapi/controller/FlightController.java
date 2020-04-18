package tr.kartal.airportticketapi.controller;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import tr.kartal.airportticketapi.model.AirlineCompany;
import tr.kartal.airportticketapi.model.Flight;
import tr.kartal.airportticketapi.repository.rsql.CustomRsqlVisitor;
import tr.kartal.airportticketapi.service.FlightService;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@Log4j2
public class FlightController {

    @Autowired
    private FlightService flightService;

    @RequestMapping(value = "flights/list", method = RequestMethod.GET)
    public List<Flight> list() {
        return flightService.getAllFlights();
    }

    @RequestMapping(value = "flights/add", method = RequestMethod.POST)
    public void create(@RequestBody Flight flight) {
        flightService.createFlight(flight);
    }

    @RequestMapping(value = "flights", method = RequestMethod.GET)
    @ResponseBody
    public List<Flight> findAllByRsql(@RequestParam(value = "search") String search) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<Flight> spec = rootNode.accept(new CustomRsqlVisitor<>());
        return flightService.findAll(spec);
    }

    @RequestMapping(value = "flights/company", method = RequestMethod.GET)
    List<Flight> getFlightsWithCompany(@RequestBody AirlineCompany company) {
        return flightService.getFlightsWithCompany(company);
    }
}
