package reservation.Controller.ticket;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reservation.DTO.ticket.TicketId;

@RestController
@RequestMapping("/ticket")
public class TicketAmountController {
    @PostMapping("/amount")
    public TicketId getAmountTicket() {
        return TicketId
                .builder()
                .ticketId("ticketId")
                .build();
    }
}
