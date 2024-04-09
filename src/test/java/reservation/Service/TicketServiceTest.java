package reservation.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class TicketServiceTest {

    @Autowired private final TicketService ticketService;

    public TicketServiceTest(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Test
    void test_supplyToken() {
    }

    @Test
    void test_poolingWaitingQueue() {
    }
}