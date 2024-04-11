package reservation.Controller.ticket;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reservation.DTO.Ticket;

@RestController
@RequestMapping("/ticket")
public class TicketReservationController {
    @PostMapping("/reservation")
    public Ticket getReservationTicket() {
        return Ticket
                .builder()
                .ticketId("ticketId")
                .build();
    }

}
