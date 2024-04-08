package reservation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reservation.Domain.Concert;

public interface ConcertRepository extends JpaRepository<Concert, Integer> {
}
