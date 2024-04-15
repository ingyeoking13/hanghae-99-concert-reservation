package reservation.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reservation.DTO.Seat;
import reservation.DTO.SeatInfo;
import reservation.DTO.ConcertShow;
import reservation.Service.SeatService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SeatController {
    @Autowired private SeatService seatService;
    @GetMapping("/concerts/{concert_id}/shows/{show_id}")
    public SeatInfo getSeatInfo(@PathVariable("show_id") Long showId) {
        return seatService.getSeatInfo( showId );
    }
}
