package reservation.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seat {

    private long id;

    @JsonProperty("seat_number")
    private int seatNumber;

    @JsonProperty("occupied_status")
    private String occupiedStatus;
}
