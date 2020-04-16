package tr.kartal.airportticketapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tr.kartal.airportticketapi.model.Airport;
import tr.kartal.airportticketapi.service.AirportService;
import tr.kartal.airportticketapi.utils.SearchCriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("api/v1")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @RequestMapping(value = "airports", method = RequestMethod.GET)
    public List<Airport> list() {
        return airportService.getAirports();
    }

    @RequestMapping(value = "airports", method = RequestMethod.POST)
    public void create(@RequestBody Airport airport) {
        airportService.createAirport(airport);
    }
    @RequestMapping(method = RequestMethod.GET, value = "airport")
    @ResponseBody
    public List<Airport> search(@RequestParam(value = "search", required = false) String search) {
        List<SearchCriteria> params = new ArrayList<>();
        if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                params.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
            }
        }
        return airportService.searchAirport(params);
    }

}
