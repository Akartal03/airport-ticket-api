package tr.kartal.airportticketapi.controller;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import tr.kartal.airportticketapi.model.AirlineCompany;
import tr.kartal.airportticketapi.repository.rsql.CustomRsqlVisitor;
import tr.kartal.airportticketapi.service.AirlineCompanyService;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class AirlineCompanyController {

    @Autowired
    private AirlineCompanyService airlineCompanyService;

    @RequestMapping(value = "airportCompanies/list", method = RequestMethod.GET)
    public List<AirlineCompany> list() {
        return airlineCompanyService.getAirlineCompanies();
    }

    @RequestMapping(value = "airportCompanies/add", method = RequestMethod.POST)
    public void create(@RequestBody AirlineCompany airlineCompany) {
        airlineCompanyService.createAirlineCompany(airlineCompany);
    }

    @RequestMapping(value = "airportCompanies", method = RequestMethod.GET)
    @ResponseBody
    public List<AirlineCompany> findAllByRsql(@RequestParam(value = "search") String search) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<AirlineCompany> spec = rootNode.accept(new CustomRsqlVisitor<>());
        return airlineCompanyService.findAll(spec);
    }

}
