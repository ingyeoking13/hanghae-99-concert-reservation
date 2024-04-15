package reservation.Controller.ticket;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reservation.DTO.Ticket;
import reservation.Service.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketReservationController {
    TicketService ticketService;
    @PostMapping("/reservation")
    public Ticket getReservationTicket(@RequestHeader("Authorization") int user) {
        reservation.DTO.Ticket result = ticketService.supplyToken("reservation", user);
        return Ticket
                .builder()
                .ticketId(result.getTicketId())
                .build();
    }

}
