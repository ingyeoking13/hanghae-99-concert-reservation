package reservation.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reservation.Domain.Seat;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaSeatInfoCoreRepository {
    @Autowired private SeatInfoRepository seatInfoRepository;

    public List<Seat> getAllSeats(long showId) {
        List<Seat> seat = seatInfoRepository.findByConcertShow_Id(showId);
        return seat;
    }

    public boolean reserveSeat(long seatId) {
        Optional<Seat> seat = seatInfoRepository.findById(seatId);
        Seat _seat = seat.get();
        _seat.setOccupiedStatus("Reserved");
        return true;
    };
}
