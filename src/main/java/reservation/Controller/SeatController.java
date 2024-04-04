package reservation.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reservation.DTO.Seat;
import reservation.DTO.SeatInfo;
import reservation.DTO.Show;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SeatController {
    @GetMapping("/seats")
    public SeatInfo getSeatInfo() {
        SeatInfo result = new SeatInfo();
        result.setShow(
                Show.builder()
                        .name("test")
                        .point(1000)
                        .dateTime(LocalDateTime.MAX).build()
        );
        List<Seat> seats = new ArrayList<>();
        seats.add(
                Seat.builder()
                        .seatNumber(1)
                        .build()

        );
        result.setSeats(seats);
        return result;
    }
}
