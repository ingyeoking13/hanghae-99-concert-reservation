package reservation.Controller.DTO.ticket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketId {
    @JsonProperty("ticket_id")
    private String ticketId;
}
