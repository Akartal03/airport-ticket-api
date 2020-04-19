package tr.kartal.airportticketapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tr.kartal.airportticketapi.model.Airport;

@Repository
@Transactional
public interface AirportRepository extends JpaRepository<Airport, Integer>, JpaSpecificationExecutor<Airport> {

}
