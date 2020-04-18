package tr.kartal.airportticketapi.controller;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import tr.kartal.airportticketapi.model.Route;
import tr.kartal.airportticketapi.repository.rsql.CustomRsqlVisitor;
import tr.kartal.airportticketapi.service.RouteService;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @RequestMapping(value = "routes/list", method = RequestMethod.GET)
    public List<Route> list() {
        return routeService.getRoutes();
    }

    @RequestMapping(value = "routes/add", method = RequestMethod.POST)
    public void create(@RequestBody Route route) {
        routeService.createRoute(route);
    }

    @RequestMapping(value = "routes", method = RequestMethod.GET)
    @ResponseBody
    public List<Route> findAllByRsql(@RequestParam(value = "search") String search) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<Route> spec = rootNode.accept(new CustomRsqlVisitor<>());
        return routeService.findAll(spec);
    }

}
