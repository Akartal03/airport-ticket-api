package tr.kartal.airportticketapi.controller;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import tr.kartal.airportticketapi.exceptions.AirportNotFoundException;
import tr.kartal.airportticketapi.model.Airport;
import tr.kartal.airportticketapi.repository.AirportRepository;
import tr.kartal.airportticketapi.repository.rsql.CustomRsqlVisitor;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class AirportController {

    @Autowired
    private AirportRepository airportRepository;

    @RequestMapping(value = "airports", method = RequestMethod.GET)
    public List<Airport> list() {
        return airportRepository.findAll();
    }

    @RequestMapping(value = "airports/add", method = RequestMethod.POST)
    public Airport create(@RequestBody Airport airport) {
        return airportRepository.save(airport);
    }

    @RequestMapping(value = "airports/{id}", method = RequestMethod.GET)
    Airport get(@PathVariable Integer id) {
        return airportRepository.findById(id)
                .orElseThrow(() -> new AirportNotFoundException(id));
    }

    @RequestMapping(value = "airport", method = RequestMethod.GET)
    @ResponseBody
    public List<Airport> findAllByRsql(@RequestParam(value = "search") String search) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<Airport> spec = rootNode.accept(new CustomRsqlVisitor<>());
        return airportRepository.findAll(spec);
    }

}
