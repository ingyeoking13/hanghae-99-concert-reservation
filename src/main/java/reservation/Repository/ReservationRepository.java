package reservation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reservation.Domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
