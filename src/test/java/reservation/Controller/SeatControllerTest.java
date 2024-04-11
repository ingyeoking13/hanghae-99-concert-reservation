package reservation.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import reservation.DTO.Seat;
import reservation.DTO.SeatInfo;
import reservation.DTO.ConcertShow;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest()
class SeatControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void test_예약가능좌석조회() throws Exception {
        SeatInfo seatInfo = new SeatInfo();

        ConcertShow concertShow = ConcertShow.builder()
                .price(1000)
                .dateTime(LocalDateTime.MAX)
                .name("test")
                .build();
        seatInfo.setConcertShow(concertShow);
        List<Seat> seats = new ArrayList<>();
        seats.add(
                Seat.builder()
                        .seatNumber(1)
                        .build()

        );
        seatInfo.setSeats(seats);

        final String result = objectMapper.writeValueAsString(
                seatInfo
        );

        mockMvc.perform(get("/seats"))
                .andExpect(status().isOk())
                .andExpect(content().json(result));
    }


}