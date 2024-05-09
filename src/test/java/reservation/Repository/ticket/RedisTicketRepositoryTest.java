package reservation.Repository.ticket;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import reservation.DTO.Exception.TokenUnavailableException;

@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
class RedisTicketRepositoryTest {

    @Container
    static GenericContainer redisContainer = new GenericContainer(DockerImageName.parse("redis:7.0.8-alpine")).withExposedPorts(6379);

    @Autowired private TicketReaderRepository ticketReaderRepository;
    @Autowired private TicketWriterRepository ticketWriterRepository;

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.redis.host", () -> redisContainer.getHost());
        registry.add("spring.redis.port", () -> String.valueOf(redisContainer.getMappedPort(6379)));
    }

    @Test
    void test_대기표_쓰기읽기() {
        // given
        ticketWriterRepository.writeNewTicket("test", 1);
        ticketWriterRepository.writeNewTicket("test", 2);
        ticketWriterRepository.writeNewTicket("test", 3);

        // when
        Long res1 = ticketReaderRepository.peekWaitingNumber("test-1");
        Long res2 = ticketReaderRepository.peekWaitingNumber("test-2");
        Long res3 = ticketReaderRepository.peekWaitingNumber("test-3");

        // then
        Assertions.assertThat(res1).isEqualTo(0);
        Assertions.assertThat(res2).isEqualTo(1);
        Assertions.assertThat(res3).isEqualTo(2);
    }

    @Test
    void test_대기표_읽은후_삭제확인(){
        ticketWriterRepository.writeNewTicket("test", 1);
        Long res1 = ticketReaderRepository.readAndDeleteWaitingNumber("test-1");
        Assertions.assertThat(res1).isEqualTo(0);
        res1 = ticketReaderRepository.readAndDeleteWaitingNumber("test-1");
        Assertions.assertThat(res1).isEqualTo(-1L);
    }
}