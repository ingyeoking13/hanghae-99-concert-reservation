package reservation.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import reservation.Controller.DTO.Show;

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
        List<Show> shows = new ArrayList<>();

        Show show = Show.builder()
                .point(1000)
                .dateTime(LocalDateTime.MAX)
                .name("test")
                .build();
        shows.add( show );
        final String result = objectMapper.writeValueAsString(
                shows
        );

        mockMvc.perform(get("/concerts"))
                .andExpect(status().isOk())
                .andExpect(content().json(result));
    }

}