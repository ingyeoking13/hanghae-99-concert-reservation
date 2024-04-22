package reservation.Repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import reservation.DTO.ConcertShow;
import reservation.Domain.Concert;
import reservation.Domain.Show;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class JpaConcertConcertShowCoreRepositoryTest {

    @Autowired private JpaConcertShowCoreRepository jpaConcertShowCoreRepository;
    private final ConcertShowRepository concertShowRepository;

    public JpaConcertConcertShowCoreRepositoryTest(){
        concertShowRepository = Mockito.mock(ConcertShowRepository.class);
        jpaConcertShowCoreRepository = new JpaConcertShowCoreRepository();
    }

    List<Show> createConcertTestData() {
        List<Show> shows = new ArrayList<>();
        Concert concert = new Concert();
        concert.setId(1L);
        concert.setName("bye");
        Show show1 = new Show();
        Show show2 = new Show();
        show1.setId(1001L);
        show1.setName("hi");
        show1.setPrice(1000);
        show1.setDateTime(LocalDateTime.MAX);
        show1.setConcert(concert);

        show2.setId(1002L);
        show2.setName("bye");
        show2.setPrice(2000);
        show2.setDateTime(LocalDateTime.MAX);
        show2.setConcert(concert);
        shows.add(show1);
        shows.add(show2);
        return shows;
    }

    @Test
    void getAllConcertShowByConcertId() {
        // given
        List<Show> shows = createConcertTestData();
        Mockito.doReturn(shows).when(concertShowRepository).getAllByConcertId(1L);

        // when
        List<ConcertShow> result = jpaConcertShowCoreRepository.getAllConcertShowByConcertId(1L);

        // then
        Assertions.assertThat(result.get(0).getId()).isEqualTo(1001L);
        Assertions.assertThat(result.get(1).getId()).isEqualTo(1002L);
        Assertions.assertThat(result.get(0).getName()).isEqualTo("hi");
        Assertions.assertThat(result.get(1).getName()).isEqualTo("bye");
        Assertions.assertThat(result.get(0).getPrice()).isEqualTo(1000);
        Assertions.assertThat(result.get(1).getPrice()).isEqualTo(2000);
        Assertions.assertThat(result.get(0).getDateTime()).isEqualTo(LocalDateTime.MAX);
        Assertions.assertThat(result.get(1).getDateTime()).isEqualTo(LocalDateTime.MAX);

    }
}