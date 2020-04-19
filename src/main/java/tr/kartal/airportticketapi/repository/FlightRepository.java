package tr.kartal.airportticketapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tr.kartal.airportticketapi.model.AirlineCompany;
import tr.kartal.airportticketapi.model.Flight;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface FlightRepository extends JpaRepository<Flight, Integer>, JpaSpecificationExecutor<Flight> {

    List<Flight> findByAirlineCompany(AirlineCompany company);

    Optional<Flight> findFlightByFlightNumber(String flightNumber);

    @Modifying
    @Query("UPDATE Flight f SET f.numberOfSoldTickets = f.numberOfSoldTickets +1 WHERE f.id = :id")
    int updateNumberOfSoldTickets(
            @Param("id") Integer id);

    @Modifying
    @Query("UPDATE Flight f SET f.ticketPrice = :ticketPrice, f.priceIncreseRatio = :priceIncreaseRatio WHERE f.id = :id")
    int updateTicketPrice(
            @Param("ticketPrice") BigDecimal price,
            @Param("priceIncreaseRatio") Integer priceIncreaseRatio,
            @Param("id") Integer id);
}
