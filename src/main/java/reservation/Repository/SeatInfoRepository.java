package reservation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reservation.Domain.Seat;

import java.util.List;

public interface SeatInfoRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByConcertShow_Id(Long id);
}
