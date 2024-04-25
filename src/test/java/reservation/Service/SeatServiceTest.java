package reservation.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.LinkedList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import reservation.DTO.ConcertShow;
import reservation.DTO.SeatInfo;
import reservation.Domain.Concert;
import reservation.Domain.Seat;
import reservation.Domain.Show;
import reservation.Repository.JpaConcertShowCoreRepository;
import reservation.Repository.JpaSeatInfoCoreRepository;

import static org.assertj.core.api.Assertions.assertThat;

class SeatServiceTest {

  SeatService seatService;

  @Mock
  private JpaSeatInfoCoreRepository jpaSeatInfoCoreRepository;

  @Mock private JpaConcertShowCoreRepository jpaConcertShowCoreRepository;

  public SeatServiceTest() {
    jpaSeatInfoCoreRepository = Mockito.mock(JpaSeatInfoCoreRepository.class);
    jpaConcertShowCoreRepository = Mockito.mock(JpaConcertShowCoreRepository.class);
    this.seatService = new SeatService(
        jpaSeatInfoCoreRepository,
        jpaConcertShowCoreRepository
    );
  }

  @Test
  void test_좌석정보가져오기(){
    Show show = new Show();
    show.setId(1L);
    Concert concert = new Concert();
    show.setConcert(concert);

    Mockito.doReturn(show).when( jpaConcertShowCoreRepository ).findById(anyLong());
    List<Seat> seats = new LinkedList<>();
    Mockito.doReturn(seats).when(jpaSeatInfoCoreRepository).getAllSeats(anyLong());

    SeatInfo seatInfo = new SeatInfo();
    List<reservation.DTO.Seat> seatsDto = seats.stream().map(seat -> seat.toSeatDTO()).toList();
    seatInfo.setSeats(seatsDto);
    seatInfo.setConcertShow(show.toConcertShow());

    assertTrue(new ReflectionEquals(seatService.getSeatInfo(1L).getSeats()).matches(seatInfo.getSeats()));
    assertTrue(new ReflectionEquals(seatService.getSeatInfo(1L).getConcertShow()).matches(seatInfo.getConcertShow()));
  }
}