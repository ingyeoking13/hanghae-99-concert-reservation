package reservation.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reservation.Domain.Show;
import reservation.Repository.ConcertShowRepository;
import reservation.Repository.JpaConcertShowCoreRepository;
import reservation.Service.DTO.ConcertShow;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConcertShowService {
    private final ConcertShowRepository concertShowRepository;

    public List<ConcertShow> getAvailableConcertShow(Long id) {
        List<Show> shows = concertShowRepository.getAllByConcertId(id);
        List<ConcertShow> results = new LinkedList<>();
        shows.stream().forEach((item) -> results.add(item.toConcertShow()));
        return results;
    }
}

