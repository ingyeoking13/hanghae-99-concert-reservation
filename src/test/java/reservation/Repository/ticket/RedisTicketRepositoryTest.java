package reservation.Repository.ticket;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisTicketRepositoryTest {

    @Autowired private TicketReaderRepository ticketReaderRepository;
    @Autowired private TicketWriterRepository ticketWriterRepository;

    @Test
    void test_대기표_쓰기읽기() {
        // given
        ticketWriterRepository.writeNewTicket("test", 1);
        ticketWriterRepository.writeNewTicket("test", 2);
        ticketWriterRepository.writeNewTicket("test", 3);

        // when
        Long res1 = ticketReaderRepository.readWaitingNumber("test-1");
        Long res2 = ticketReaderRepository.readWaitingNumber("test-2");
        Long res3 = ticketReaderRepository.readWaitingNumber("test-3");

        // then
        Assertions.assertThat(res1).isEqualTo(1);
        Assertions.assertThat(res2).isEqualTo(2);
        Assertions.assertThat(res3).isEqualTo(3);
    }
}