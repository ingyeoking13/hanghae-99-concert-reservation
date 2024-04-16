package reservation.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reservation.Domain.Reservation;
import reservation.Domain.Seat;
import reservation.Domain.Show;
import reservation.Domain.User;

@Repository
public class JpaReservationCoreRepository {
    @Autowired private ReservationRepository reservationRepository;

    public boolean saveReservationInfo(Seat seat, Show show, User user){
        Reservation reservation = Reservation.builder()
                    .seat(seat)
                    .seatNumber(seat.getSeatNumber())
                    .show(show)
                    .showName(show.getName())
                    .user( user )
                    .payment(show.getPrice())
                    .build();
        reservationRepository.save( reservation );
        return true;
    }

}
