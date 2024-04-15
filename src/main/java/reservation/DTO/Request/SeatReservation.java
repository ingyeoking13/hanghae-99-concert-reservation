package reservation.DTO.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeatReservation {

    @JsonProperty("concert_id")
    long concertId;

    @JsonProperty("show_id")
    long showId;

    @JsonProperty("seat_id")
    long seatId;
}
