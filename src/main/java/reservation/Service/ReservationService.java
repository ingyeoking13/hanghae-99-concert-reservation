package reservation.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reservation.Repository.JpaSeatInfoCoreRepository;

@Service
public class ReservationService {
    @Autowired private JpaSeatInfoCoreRepository jpaSeatInfoCoreRepository;

    public boolean reserveSeat(long seatId) {
        return jpaSeatInfoCoreRepository.reserveSeat(seatId);
    }
}
