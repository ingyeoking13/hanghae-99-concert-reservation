package reservation.Service;

import jakarta.validation.constraints.Null;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import reservation.DTO.Exception.AlreadyOccupiedException;
import reservation.Domain.Seat;
import reservation.Domain.Show;
import reservation.Domain.User;
import reservation.Repository.JpaReservationCoreRepository;
import reservation.Repository.JpaSeatInfoCoreRepository;
import reservation.Repository.JpaUserCoreRepository;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {
    private final ReservationService reservationService;
    @Mock private JpaSeatInfoCoreRepository jpaSeatInfoCoreRepository;
    @Mock private JpaReservationCoreRepository jpaReservationCoreRepository;
    @Mock private JpaUserCoreRepository jpaUserCoreRepository;

    public ReservationServiceTest(){
        jpaSeatInfoCoreRepository = Mockito.mock(JpaSeatInfoCoreRepository.class);
        jpaReservationCoreRepository = Mockito.mock(JpaReservationCoreRepository.class);
        jpaUserCoreRepository = Mockito.mock(JpaUserCoreRepository.class);
        this.reservationService = new ReservationService(
                jpaSeatInfoCoreRepository,
                jpaReservationCoreRepository,
                jpaUserCoreRepository
        );
    }

    @Test
    public void test_예약하기() throws Exception {
        // given
        Mockito.doReturn(Mockito.mock(Seat.class)).when(jpaSeatInfoCoreRepository).reserveSeat(1L);
        Mockito.doReturn(Mockito.mock(Seat.class)).when(jpaSeatInfoCoreRepository).findById(1L);
        Mockito.doReturn(Mockito.mock(User.class)).when(jpaUserCoreRepository).getById(1L);
        Mockito.doReturn(true).when(jpaReservationCoreRepository).saveReservationInfo(
                Mockito.any(Seat.class),
                Mockito.any(Show.class),
                Mockito.any(User.class)
        );
        // when
        boolean result = reservationService.reserveSeat(1L, 1L);
        // then
        Assertions.assertThat(result).isEqualTo(true);
    }

    @Test
    public void test_이미점유된좌석(){
        // given
        Mockito.doThrow(NullPointerException.class).when(jpaSeatInfoCoreRepository).reserveSeat(1L); // when then
        Assertions.assertThatThrownBy(
                () -> reservationService.reserveSeat(1L, 1L)
        ).isInstanceOf(AlreadyOccupiedException.class);
    }

}