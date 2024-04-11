package reservation.Controller.ticket;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reservation.DTO.Ticket;

@RestController
@RequestMapping("/ticket")
public class TicketSeatController {

    @PostMapping("/seats")
    public Ticket getSeatTicket() {
        return Ticket
                .builder()
                .ticketId("ticketId")
                .build();
    }

}
