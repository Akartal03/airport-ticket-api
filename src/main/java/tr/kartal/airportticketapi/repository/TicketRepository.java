package tr.kartal.airportticketapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tr.kartal.airportticketapi.model.Ticket;

import java.util.Optional;

@Repository
@Transactional
public interface TicketRepository extends JpaRepository<Ticket, Integer>, JpaSpecificationExecutor<Ticket> {

    @Modifying
    @Query("UPDATE Ticket t SET t.isCancel = true WHERE t.ticketNumber = :ticketNumber")
    int updateTicketStatu(
            @Param("ticketNumber") String ticketNumber);

    @Query("SELECT max(a.id) FROM Ticket a ")
    Optional<Integer> select();

}
