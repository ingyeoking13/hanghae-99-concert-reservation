package reservation.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reservation.Controller.util.TokenManager;
import reservation.DTO.Exception.AlreadyOccupiedException;
import reservation.Domain.Seat;
import reservation.Domain.User;
import reservation.Repository.JpaReservationCoreRepository;
import reservation.Repository.JpaSeatInfoCoreRepository;
import reservation.Repository.JpaUserCoreRepository;

@Service
@Transactional
public class ReservationService {
    @Autowired private JpaSeatInfoCoreRepository jpaSeatInfoCoreRepository;
    @Autowired private JpaReservationCoreRepository jpaReservationCoreRepository;
    @Autowired private JpaUserCoreRepository jpaUserCoreRepository;

    public boolean reserveSeat(long seatId, String tokenId) throws Exception{
        try {
            Seat result = jpaSeatInfoCoreRepository.reserveSeat(seatId);
        } catch(NullPointerException e) {
            throw new AlreadyOccupiedException();
        }
        finally {
            Seat seat = jpaSeatInfoCoreRepository.findById(seatId);
            TokenManager tm = TokenManager.builder().build();
            User user = jpaUserCoreRepository.getById(Long.valueOf(tm.getUserId(tokenId)));
            jpaReservationCoreRepository.saveReservationInfo(
                    seat,
                    seat.getConcertShow(),
                    user
            );
        }
        return true;
    }
}
