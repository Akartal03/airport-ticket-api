package tr.kartal.airportticketapi.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tr.kartal.airportticketapi.model.AirlineCompany;
import tr.kartal.airportticketapi.repository.AirlineCompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class AirlineCompanyService {

    @Autowired
    private AirlineCompanyRepository airlineCompanyRepository;

    public List<AirlineCompany> getAirlineCompanies() {
        return airlineCompanyRepository.findAll();
    }

    public void createAirlineCompany(AirlineCompany airlineCompany) {
        try {
            airlineCompanyRepository.save(airlineCompany);
            log.info("airport saved, name :" + airlineCompany.toString());
        } catch (Exception ex) {
            log.error("airport save error," + ex.getMessage());
        }
    }

    public AirlineCompany selectAirport(Integer id) {
        Optional<AirlineCompany> airlineCompany = airlineCompanyRepository.findById(id);
        return airlineCompany.orElse(null);
    }

    public List<AirlineCompany> findAll(Specification<AirlineCompany> spec) {
        return airlineCompanyRepository.findAll(spec);
    }
}


