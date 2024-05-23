package reservation.Repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reservation.Domain.Reservation;
import reservation.Domain.Seat;
import reservation.Domain.Show;
import reservation.Domain.User;

@Repository
@Slf4j
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
        return reservation;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Reservation rollbackSavePaymentReservationInfo(Long userId) {
        Reservation reservation = reservationRepository.findByUser_Id(userId).get();
        reservation.getSeat().setOccupiedStatus("Reserved");
        return reservation;
    }

    public Reservation getUserReservationInfo(Long reservationId) {
        return reservationRepository.findById(reservationId).get();
    }

}
