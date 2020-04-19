package tr.kartal.airportticketapi.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tr.kartal.airportticketapi.model.Flight;
import tr.kartal.airportticketapi.model.Ticket;
import tr.kartal.airportticketapi.repository.FlightRepository;
import tr.kartal.airportticketapi.repository.TicketRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private FlightRepository flightRepository;

    public Ticket selectTicket(Integer id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        return ticket.orElse(null);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void buyTicket(Ticket ticket) {
        try {
            Optional<Flight> flight = flightRepository.findFlightByFlightNumber(ticket.getFlight().getFlightNumber());
            if (flight.isPresent()) {
                synchronized (flight.get()) {
                    if (flight.get().getQuota() > flight.get().getNumberOfSoldTickets()) {
                        if (flightRepository.updateNumberOfSoldTickets(flight.get().getId()) == -1) {
                            log.error("flight can not updated !!");
                            return;
                        }
                    } else {
                        log.info("flight is full, sorry !!");
                        return;
                    }
                    ticketRepository.save(ticket);
                    log.info("ticket saved,  :" + ticket.toString());

                    checkFlightOccupancyRate(flight.get());
                }
            }
            log.error("flight can not find !!");
        } catch (Exception ex) {
            log.error("ticket save error," + ex.getMessage());
        }
    }

    public List<Ticket> findAll(Specification<Ticket> spec) {
        return ticketRepository.findAll(spec);
    }

    // herbir bilet alındığında uçuş doluluk oranını kontrol eder ve gerektiğinde bilet fiyatında yüzde 10 artış sağlar.
    private synchronized void checkFlightOccupancyRate(Flight flight) {
        if (flight.getQuota() / (flight.getPriceIncreseRatio() + 1) * 10 <= (flight.getNumberOfSoldTickets() + 1)) {
            BigDecimal price = flight.getTicketPrice().add(flight.getTicketPrice().divide(new BigDecimal(10), 2, RoundingMode.HALF_UP));
            Integer priceIncreaseRatio = flight.getPriceIncreseRatio() + 1;
            if (flightRepository.updateTicketPrice(price, priceIncreaseRatio, flight.getId()) == -1) {
                log.error("updateTicketPrice error!");
            }
        }
    }
}
