package reservation.Controller.ticket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import reservation.Controller.DTO.ticket.TicketId;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TicketReservationController.class)
class TicketReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void test_예약_티켓발급() throws Exception {
        final String result = objectMapper.writeValueAsString(
                TicketId.builder().ticketId("ticketId").build()
        );

        mockMvc.perform(post("/ticket/reservation"))
                .andExpect(status().isOk())
                .andExpect(content().json(result));
    }

}