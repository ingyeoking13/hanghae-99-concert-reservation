package reservation.Controller.ticket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import reservation.DTO.BaseResponse;
import reservation.DTO.Ticket;
import reservation.Service.TicketService;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TicketSeatController.class)
class TicketSeatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void test_예약가능좌석조회_티켓발급() throws Exception {
        Mockito.doReturn(
            Ticket.builder()
                .ticketId("seats-1").build()
        ).when(ticketService).supplyToken(anyString(), anyInt());

        BaseResponse<Ticket> response = new BaseResponse<>();
        response.setResult( Ticket.builder().ticketId("seats-1").build() );

        final String result = objectMapper.writeValueAsString(
            response
        );

        mockMvc.perform(post("/ticket/seats").header(
                "Authorization", "1"
            ))
            .andExpect(status().isOk())
            .andExpect(content().json(result));

    }
}