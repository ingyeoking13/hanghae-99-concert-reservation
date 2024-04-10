package reservation.Repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reservation.Domain.Concert;
import reservation.Domain.Show;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaConcertCoreRepository implements ConcertCoreRepository {
    private final ConcertRepository concertRepository;

}
