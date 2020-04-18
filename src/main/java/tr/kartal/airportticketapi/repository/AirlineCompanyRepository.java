package tr.kartal.airportticketapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tr.kartal.airportticketapi.model.AirlineCompany;
import tr.kartal.airportticketapi.model.Airport;

import java.util.Optional;

@RepositoryRestResource
public interface AirlineCompanyRepository extends JpaRepository<AirlineCompany, Integer>, JpaSpecificationExecutor<AirlineCompany> {

    Optional<Airport> findByName(String name);

}
