package reservation.Controller;

import io.swagger.v3.oas.annotations.headers.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reservation.Controller.util.TokenManager;
import reservation.DTO.Request.SeatReservation;
import reservation.Service.ReservationService;

@RestController
public class ReservationController {
    @Autowired private ReservationService reservationService;

    @PostMapping("/reservation")
    public boolean reservation(
            @RequestHeader("token_id") String tokenId,
            @RequestBody SeatReservation seatReservation) throws Exception {

        TokenManager tm = TokenManager.builder().build();
        Long userId = Long.valueOf(tm.getUserId(tokenId));
        reservationService.reserveSeat(seatReservation.getSeatId(), userId);
        return true;
    }


}
