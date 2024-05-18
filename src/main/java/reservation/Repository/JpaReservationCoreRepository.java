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
                    .user(user)
                    .payment(show.getPrice())
                    .build();
        reservation.getSeat().setOccupiedStatus("Reserved");
        reservation.setPayment(show.getPrice());
        reservationRepository.save( reservation );
        return true;
    }

    public Reservation savePaymentReservationInfo(Long userId) {
        Reservation reservation = reservationRepository.findByUser_Id(userId).get();
        reservation.getSeat().setOccupiedStatus("Confirmed");
        reservationRepository.save( reservation );
        return reservation;
    }

    public Reservation rollbackSavePaymentReservationInfo(Long userId) {
        Reservation reservation = reservationRepository.findByUser_Id(userId).get();
        reservation.getSeat().setOccupiedStatus("Reserved");
        reservationRepository.save( reservation );
        return reservation;
    }

    public Reservation getUserReservationInfo(Long reservationId) {
        return reservationRepository.findById(reservationId).get();
    }

}
