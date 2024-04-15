package reservation.Repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reservation.DTO.ConcertShow;
import reservation.Domain.Show;

import java.util.LinkedList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaConcertShowCoreRepository {
    private final ConcertShowRepository concertShowRepository;


    public List<ConcertShow> getAllConcertShowByConcertId(Long concertId) {
        List<Show> shows = concertShowRepository.getAllByConcertId(concertId);
        List<ConcertShow> result = new LinkedList<>();
        shows.stream().forEach(
                (show)-> result.add(show.toConcertShow())
        );
        return result;
    }

    public Show findById(long showId){
        return concertShowRepository.findById(showId).get();
    }
}
