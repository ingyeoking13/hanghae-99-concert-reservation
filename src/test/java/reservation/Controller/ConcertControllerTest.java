package reservation.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import reservation.DTO.ConcertShow;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConcertController.class)
class ConcertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void test_충전_티켓발급() throws Exception {
        List<ConcertShow> concertShows = new ArrayList<>();

        ConcertShow concertShow = ConcertShow.builder()
                .point(1000)
                .dateTime(LocalDateTime.MAX)
                .name("test")
                .build();
        concertShows.add(concertShow);
        final String result = objectMapper.writeValueAsString(
                concertShows
        );

        mockMvc.perform(get("/concerts"))
                .andExpect(status().isOk())
                .andExpect(content().json(result));
    }

}