package reservation.Service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import reservation.Repository.ticket.RedisTicketReaderRepository;
import reservation.Repository.ticket.RedisTicketWriterRepository;
import reservation.DTO.Ticket;
import reservation.DTO.Exception.TokenUnavailableException;
import reservation.DTO.Exception.WaitingException;

class TicketServiceTest {

    private final TicketService ticketService;
    @Mock private RedisTicketReaderRepository redisTicketReaderRepository;
    @Mock private RedisTicketWriterRepository redisTicketWriterRepository;

    public TicketServiceTest() {
        redisTicketReaderRepository = Mockito.mock(RedisTicketReaderRepository.class);
        redisTicketWriterRepository = Mockito.mock(RedisTicketWriterRepository.class);
        this.ticketService = new TicketService(
                redisTicketReaderRepository,
                redisTicketWriterRepository
        );
    }

    @Test
    void test_supplyToken() {
        String serviceName = "test";
        int userId = 1;
        Mockito.doReturn(serviceName + "-" + userId)
                .when(redisTicketWriterRepository).writeNewTicket(serviceName, userId);
        Ticket result = ticketService.supplyToken(serviceName, userId);
        Assertions.assertThat(result.getTicketId()).isEqualTo(
                serviceName + '-' + userId
        );
    }


    @Test
    void test_토큰이없는경우() {
        String serviceName = "test";
        String token = "test-1";
        Mockito.doReturn(-1L).when(redisTicketReaderRepository).readAndDeleteWaitingNumber(
                token
        );
        Assertions.assertThatThrownBy(
                () ->ticketService.poolingWaitingQueue(token)
        ).isInstanceOf(TokenUnavailableException.class);
    }

    @Test
    void test_토큰이풀사이즈를넘는경우() {
        String serviceName = "test";
        String token = "test-1";
        long expectResult = 10000L;
        Mockito.doReturn(expectResult).when(redisTicketReaderRepository).readAndDeleteWaitingNumber(
                token
        );
        Assertions.assertThatThrownBy(
                () ->ticketService.poolingWaitingQueue(token)
        ).isInstanceOf(WaitingException.class).hasFieldOrPropertyWithValue(
                "waitingNumber",(int) expectResult
        );
    }

    @Test
    void test_토큰이풀사이즈안에들어오는경우() throws Exception {
        String serviceName = "test";
        String token = "test-1";
        long expectResult = 0L;
        Mockito.doReturn(expectResult).when(redisTicketReaderRepository).readAndDeleteWaitingNumber(
                token
        );
        Assertions.assertThat( ticketService.poolingWaitingQueue(token) ).isEqualTo(true);
    }
}