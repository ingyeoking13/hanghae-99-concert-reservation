package reservation.Service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reservation.DTO.SeatInfo;
import reservation.Domain.Seat;
import reservation.Domain.Show;
import reservation.Repository.JpaConcertShowCoreRepository;
import reservation.Repository.JpaSeatInfoCoreRepository;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SeatService {
    @Autowired private JpaSeatInfoCoreRepository jpaSeatInfoCoreRepository;
    @Autowired private JpaConcertShowCoreRepository jpaConcertShowCoreRepository;

    public SeatInfo getSeatInfo(long showId) {
        Show show = jpaConcertShowCoreRepository.findById(showId);

        List<Seat> seats = jpaSeatInfoCoreRepository.getAllSeats(show.getId());
        SeatInfo result = new SeatInfo();
        List<reservation.DTO.Seat> seatsDto = seats.stream().map(seat ->  seat.toSeatDTO()).toList();
        result.setConcertShow(show.toConcertShow());
        result.setSeats( seatsDto );

        return result;
    }


}
