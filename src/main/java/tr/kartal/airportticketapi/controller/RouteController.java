package tr.kartal.airportticketapi.controller;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import tr.kartal.airportticketapi.exceptions.RouteNotFoundException;
import tr.kartal.airportticketapi.model.Route;
import tr.kartal.airportticketapi.repository.RouteRepository;
import tr.kartal.airportticketapi.repository.rsql.CustomRsqlVisitor;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class RouteController {

    @Autowired
    private RouteRepository routeRepository;

    @RequestMapping(value = "routes", method = RequestMethod.GET)
    public List<Route> list() {
        return routeRepository.findAll();
    }

    @RequestMapping(value = "routes/add", method = RequestMethod.POST)
    public void create(@RequestBody Route route) {
        routeRepository.save(route);
    }

    @RequestMapping(value = "routes/{id}", method = RequestMethod.GET)
    Route get(@PathVariable Integer id) {
        return routeRepository.findById(id)
                .orElseThrow(() -> new RouteNotFoundException(id));
    }

    @RequestMapping(value = "route", method = RequestMethod.GET)
    @ResponseBody
    public List<Route> findAllByRsql(@RequestParam(value = "search") String search) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<Route> spec = rootNode.accept(new CustomRsqlVisitor<>());
        return routeRepository.findAll(spec);
    }

}
