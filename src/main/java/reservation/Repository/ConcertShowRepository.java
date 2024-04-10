package reservation.Repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import reservation.Domain.Show;

import java.util.List;

public interface ConcertShowRepository extends JpaRepository<Show, Long> {

    @Query("select * from from Show s where s.concertId = :concertId")
    List<Show> getAllByConcertId(@Param("concertId") Long concertId);
}
