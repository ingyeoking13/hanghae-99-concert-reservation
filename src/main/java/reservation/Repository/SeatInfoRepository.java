package reservation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import reservation.Domain.Seat;

import java.util.List;

public interface SeatInfoRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByConcertShow_Id(Long id);

    @Query("select s from Seat s where s.concertShow.id = :concertShowId and s.occupiedStatus = :status")
    List<Seat> findByConcertShowIdWithStatus(
        @Param("concertShowId") Long concertShowId,
        @Param("status") String status);
}
