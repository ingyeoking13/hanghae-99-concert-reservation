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
public class TicketPaymentController {
    @Autowired private TicketService ticketService;
    @PostMapping("/payment")
    public Ticket getPaymentTicket(@RequestHeader("Authorization") int user) {
        reservation.DTO.Ticket result = ticketService.supplyToken("payment", user);
        return Ticket
                .builder()
                .ticketId(result.getTicketId())
                .build();
    }
}
