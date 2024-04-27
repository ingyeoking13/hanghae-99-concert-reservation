package reservation.Controller.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reservation.DTO.Ticket;
import reservation.Service.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketConcertController {

    @Autowired private final TicketService ticketService;

    public TicketConcertController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/concert")
    public Ticket getConcertTicket(@RequestHeader("Authorization") int user) {
        reservation.DTO.Ticket result = ticketService.supplyToken("concert", user);
        return Ticket
                .builder()
                .ticketId(result.getTicketId())
                .build();
    }
}
