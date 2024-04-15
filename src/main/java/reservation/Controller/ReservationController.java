package reservation.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reservation.DTO.Request.SeatReservation;
import reservation.Service.ReservationService;

@RestController
public class ReservationController {
    @Autowired private ReservationService reservationService;

    @PostMapping("/reservation")
    public boolean reservation(SeatReservation seatReservation) {
        try {
            reservationService.reserveSeat(seatReservation.getSeatId());
        }
        catch (Exception e){
            return false;
        }
        return true;
    }


}
