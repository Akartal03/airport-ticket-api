package tr.kartal.airportticketapi.controller;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import tr.kartal.airportticketapi.exceptions.PassengerNotFoundException;
import tr.kartal.airportticketapi.model.Passenger;
import tr.kartal.airportticketapi.repository.PassengerRepository;
import tr.kartal.airportticketapi.repository.rsql.CustomRsqlVisitor;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@Log4j2
public class PassengerController {

    @Autowired
    private PassengerRepository passengerRepository;

    @RequestMapping(value = "passengers", method = RequestMethod.GET)
    public List<Passenger> list() {
        return passengerRepository.findAll();
    }

    @RequestMapping(value = "passengers/add", method = RequestMethod.POST)
    public void create(@RequestBody Passenger passenger) {
        passengerRepository.save(passenger);
    }

    @RequestMapping(value = "passengers/{id}", method = RequestMethod.GET)
    Passenger get(@PathVariable Integer id) {
        return passengerRepository.findById(id)
                .orElseThrow(() -> new PassengerNotFoundException(id));
    }

    @RequestMapping(value = "passenger", method = RequestMethod.GET)
    @ResponseBody
    public List<Passenger> findAllByRsql(@RequestParam(value = "search") String search) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<Passenger> spec = rootNode.accept(new CustomRsqlVisitor<>());
        return passengerRepository.findAll(spec);
    }

}
