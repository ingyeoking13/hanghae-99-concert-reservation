package reservation.Controller.ticket;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reservation.Controller.DTO.ticket.TicketId;
import reservation.Service.DTO.Ticket;
import reservation.Service.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketConcertController {
    TicketService ticketService;

    @PostMapping("/concert")
    public TicketId getConcertTicket(@RequestHeader("Authorization") int user) {
        Ticket result = ticketService.supplyToken("concert", user);
        return TicketId
                .builder()
                .ticketId(result.getTicketNumber())
                .build();
    }
}
