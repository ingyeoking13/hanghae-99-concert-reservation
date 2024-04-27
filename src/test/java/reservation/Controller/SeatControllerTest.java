package reservation.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import reservation.DTO.BaseResponse;
import reservation.DTO.Seat;
import reservation.DTO.SeatInfo;
import reservation.DTO.ConcertShow;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import reservation.Repository.JpaConcertShowCoreRepository;
import reservation.Repository.JpaSeatInfoCoreRepository;
import reservation.Service.SeatService;
import reservation.Service.TicketService;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SeatController.class)
class SeatControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean private SeatService seatService;
    @MockBean private TicketService ticketService;
    @Autowired private ObjectMapper objectMapper;

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

        Mockito.doReturn( true ).when(ticketService).poolingWaitingQueue(anyString());
        Mockito.doReturn( seatInfo ).when(seatService).getSeatInfo(anyLong());
        BaseResponse<SeatInfo> result = new BaseResponse<>();
        result.setResult( seatInfo );

        final String resultStr = objectMapper.writeValueAsString( result );

        mockMvc.perform(get("/concerts/1/shows/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(resultStr));
    }


}