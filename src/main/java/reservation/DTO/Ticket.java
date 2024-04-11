package reservation.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @JsonProperty("ticket_id")
    private String ticketId;
}
