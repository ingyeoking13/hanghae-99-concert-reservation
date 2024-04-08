package reservation.Repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaConcertCoreRepository implements ConcertCoreRepository {
    private final ConcertRepository concertRepository;



}
