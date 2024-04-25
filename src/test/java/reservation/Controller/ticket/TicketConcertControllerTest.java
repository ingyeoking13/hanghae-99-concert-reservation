package reservation.Controller.ticket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import reservation.DTO.Ticket;
import reservation.Service.TicketService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TicketConcertController.class)
class TicketConcertControllerTest {

    @Autowired private MockMvc mockMvc;


    @MockBean private TicketService ticketService;

    @Autowired private ObjectMapper objectMapper;


    @Test
    void test_예약가능날짜조회_티켓발급() throws Exception {
        final String result = objectMapper.writeValueAsString(
                Ticket.builder().ticketId("test-1").build()
        );

        mockMvc.perform(post("/ticket/concert"))
                .andExpect(status().isOk())
                .andExpect(content().json(result));

    }
}