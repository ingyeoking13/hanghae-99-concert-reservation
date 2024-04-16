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

    public Seat reserveSeat(long seatId) {
        Optional<Seat> seat = seatInfoRepository.findById(seatId);
        Seat _seat = seat.get();
        String status = _seat.getOccupiedStatus();
        if (!status.equalsIgnoreCase("Empty")) {
            return null;
        }
        _seat.setOccupiedStatus("PreReserved");
        seatInfoRepository.save(_seat);
        return _seat;
    };

    public Seat findById(long seatId){
        return seatInfoRepository.findById(seatId).get();
    }
}
