package reservation.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeatInfo {
    private Show show;
    private List<Seat> seats;
}
