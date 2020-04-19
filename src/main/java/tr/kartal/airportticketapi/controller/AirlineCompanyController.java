package tr.kartal.airportticketapi.controller;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import tr.kartal.airportticketapi.exceptions.AirlineCompanyNotFoundException;
import tr.kartal.airportticketapi.model.AirlineCompany;
import tr.kartal.airportticketapi.repository.AirlineCompanyRepository;
import tr.kartal.airportticketapi.repository.rsql.CustomRsqlVisitor;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class AirlineCompanyController {

    @Autowired
    private AirlineCompanyRepository airlineCompanyRepository;

    @RequestMapping(value = "airportCompanies", method = RequestMethod.GET)
    public List<AirlineCompany> list() {
        return airlineCompanyRepository.findAll();
    }

    @RequestMapping(value = "airportCompanies/add", method = RequestMethod.POST)
    public AirlineCompany create(@RequestBody AirlineCompany airlineCompany) {
        return airlineCompanyRepository.save(airlineCompany);
    }

    @RequestMapping(value = "airportCompanies/{id}", method = RequestMethod.GET)
    AirlineCompany get(@PathVariable Integer id) {
        return airlineCompanyRepository.findById(id)
                .orElseThrow(() -> new AirlineCompanyNotFoundException(id));
    }

    @RequestMapping(value = "airportCompany", method = RequestMethod.GET)
    @ResponseBody
    public List<AirlineCompany> findAllByRsql(@RequestParam(value = "search") String search) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<AirlineCompany> spec = rootNode.accept(new CustomRsqlVisitor<>());
        return airlineCompanyRepository.findAll(spec);
    }

}
