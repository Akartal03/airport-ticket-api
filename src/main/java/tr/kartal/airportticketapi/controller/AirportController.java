package tr.kartal.airportticketapi.controller;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import tr.kartal.airportticketapi.model.Airport;
import tr.kartal.airportticketapi.repository.rsql.CustomRsqlVisitor;
import tr.kartal.airportticketapi.service.AirportService;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @RequestMapping(value = "airports/list", method = RequestMethod.GET)
    public List<Airport> list() {
        return airportService.getAirports();
    }

    @RequestMapping(value = "airports/add", method = RequestMethod.POST)
    public void create(@RequestBody Airport airport) {
        airportService.createAirport(airport);
    }

    @RequestMapping(value = "airports", method = RequestMethod.GET)
    @ResponseBody
    public List<Airport> findAllByRsql(@RequestParam(value = "search") String search) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<Airport> spec = rootNode.accept(new CustomRsqlVisitor<>());
        return airportService.findAll(spec);
    }

}
