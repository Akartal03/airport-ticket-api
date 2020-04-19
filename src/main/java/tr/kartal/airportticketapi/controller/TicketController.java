package tr.kartal.airportticketapi.controller;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import tr.kartal.airportticketapi.model.Ticket;
import tr.kartal.airportticketapi.repository.TicketRepository;
import tr.kartal.airportticketapi.repository.rsql.CustomRsqlVisitor;
import tr.kartal.airportticketapi.service.TicketService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1")
@Log4j2
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketService ticketService;

    @RequestMapping(value = "tickets", method = RequestMethod.GET)
    public List<Ticket> list() {
        return ticketRepository.findAll()
                .stream()
                .filter(x -> !x.getIsCancel() && x.getFlight().getArrivalDate().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "tickets/buy", method = RequestMethod.POST)
    public void buyTicket(@RequestBody Ticket ticket) {
        ticketService.buyTicket(ticket);
    }

    @RequestMapping(value = "tickets/cancel/{ticketNumber}", method = RequestMethod.POST)
    public void cancel(@PathVariable String ticketNumber) throws Exception {
        if (ticketRepository.updateTicketStatu(ticketNumber) == -1) {
            throw new Exception("ticket cancel error!");
        } else {
            log.info("ticket canceled:" + ticketNumber);
        }
    }

    @RequestMapping(value = "ticket", method = RequestMethod.GET)
    @ResponseBody
    public List<Ticket> findAllByRsql(@RequestParam(value = "search") String search) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<Ticket> spec = rootNode.accept(new CustomRsqlVisitor<>());
        return ticketRepository.findAll(spec);
    }

}
