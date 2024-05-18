package reservation.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import reservation.Domain.Account;
import reservation.Domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
  Optional<Reservation> findByUser_Id(Long id);
}
