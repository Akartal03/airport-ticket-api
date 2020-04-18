package tr.kartal.airportticketapi.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tr.kartal.airportticketapi.model.Route;
import tr.kartal.airportticketapi.repository.RouteRepository;


import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public List<Route> getRoutes() {
        return routeRepository.findAll();
    }

    public void createRoute(Route route) {
        try {
            routeRepository.save(route);
            log.info("route saved, name :" + route.toString());
        } catch (Exception ex) {
            log.error("route save error," + ex.getMessage());
        }
    }

    public Route selectroute(Integer id) {
        Optional<Route> route = routeRepository.findById(id);
        return route.orElse(null);
    }

    public List<Route> findAll(Specification<Route> spec) {
        return routeRepository.findAll(spec);
    }
}


