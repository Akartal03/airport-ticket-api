package tr.kartal.airportticketapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tr.kartal.airportticketapi.model.Passenger;

@Repository
@Transactional
public interface PassengerRepository extends JpaRepository<Passenger, Integer>, JpaSpecificationExecutor<Passenger> {

}
