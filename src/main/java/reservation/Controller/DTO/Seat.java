package reservation.Controller.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
    @JsonProperty("seat_number")
    private int seatNumber;
}
