package reservation.Repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reservation.DTO.ConcertShow;
import reservation.Domain.Show;

import java.util.LinkedList;
import java.util.List;

@Repository
public class JpaConcertShowCoreRepository {
    @Autowired private ConcertShowRepository concertShowRepository;


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
