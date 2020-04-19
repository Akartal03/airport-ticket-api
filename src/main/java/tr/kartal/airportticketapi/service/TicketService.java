package tr.kartal.airportticketapi.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tr.kartal.airportticketapi.model.Flight;
import tr.kartal.airportticketapi.model.Ticket;
import tr.kartal.airportticketapi.repository.FlightRepository;
import tr.kartal.airportticketapi.repository.TicketRepository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Log4j2
public class TicketService {

    private static AtomicInteger ticketCounter = new AtomicInteger();

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private FlightRepository flightRepository;

    @PostConstruct
    void init(){
        if(ticketRepository.select().isPresent()){
            ticketCounter = new AtomicInteger(ticketRepository.select().get());
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void buyTicket(Ticket ticket) {
        try {
            Optional<Flight> flight = flightRepository.findById(ticket.getFlight().getId());
            if (flight.isPresent()) {
                if (flight.get().getQuota() > flight.get().getNumberOfSoldTickets()) {
                    ticket.setTicketNumber(ticketCounter.incrementAndGet());
                    ticketRepository.save(ticket);
                    log.info("ticket saved,  :" + ticket.toString());

                    synchronized (flight.get()) {
                        if (flightRepository.updateNumberOfSoldTickets(flight.get().getId()) == -1) {
                            log.error("flight can not updated !!");
                            return;
                        }
                    }
                } else {
                    log.info("flight is full, sorry !!");
                    return;
                }

                checkFlightOccupancyRate(flight.get());
            } else {
                log.error("flight can not find !!");
            }
        } catch (Exception ex) {
            log.error("ticket save error," + ex.getMessage());
        }
    }

    // herbir bilet alındığında uçuş doluluk oranını kontrol eder ve gerektiğinde bilet fiyatında yüzde 10 artış sağlar.
    private synchronized void checkFlightOccupancyRate(Flight flight) {
        if (flight.getPriceIncreseRatio() == 100) {
            return;
        }
        if ((flight.getQuota() * flight.getPriceIncreseRatio()) / 100 <= (flight.getNumberOfSoldTickets() + 1)) {
            BigDecimal price = flight.getTicketPrice().add(flight.getTicketPrice().divide(new BigDecimal(10), 2, RoundingMode.HALF_UP));
            Integer priceIncreaseRatio = flight.getPriceIncreseRatio() + 10;

            if (flightRepository.updateTicketPrice(price, priceIncreaseRatio, flight.getId()) == -1) {
                log.error("updateTicketPrice error!");
            }
        }
    }
}
