package reservation.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import reservation.DTO.ConcertShow;
import reservation.Service.ConcertShowService;
import reservation.Service.TicketService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConcertController.class)
class ConcertControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private TicketService ticketService;
    @MockBean private ConcertShowService concertShowService;
    @Autowired private ObjectMapper objectMapper;

    @Test
    void test_충전_티켓발급() throws Exception {
        // given - when
        List<ConcertShow> concertShows = new ArrayList<>();
        ConcertShow concertShow = ConcertShow.builder()
                .price(1000)
                .dateTime(LocalDateTime.MAX)
                .name("test")
                .build();
        concertShows.add(concertShow);
        Mockito.doReturn(
                true
        ).when(ticketService).poolingWaitingQueue("test", "test-1");
        Mockito.doReturn(concertShows).when(concertShowService).getAvailableConcertShow(
                1L
        );

        final String result = objectMapper.writeValueAsString(
                concertShows
        );

        // then
        mockMvc.perform(get("/concerts/1")
                        .header("Authorization", 1)
                        .header( "token_id", 1)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(result));
    }

}