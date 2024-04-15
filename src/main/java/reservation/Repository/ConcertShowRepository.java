package reservation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import reservation.Domain.Show;

import java.util.List;

public interface ConcertShowRepository extends JpaRepository<Show, Long> {

    @Query("select s from Show s where s.concert.id = :concertId")
    List<Show> getAllByConcertId(@Param("concertId") Long concertId);
}