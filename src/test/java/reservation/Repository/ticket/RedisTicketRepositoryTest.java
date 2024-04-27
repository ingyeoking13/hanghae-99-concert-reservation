package reservation.Repository.ticket;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

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
        Long res1 = ticketReaderRepository.readWaitingNumber("test-1");
        Long res2 = ticketReaderRepository.readWaitingNumber("test-2");
        Long res3 = ticketReaderRepository.readWaitingNumber("test-3");

        // then
        Assertions.assertThat(res1).isEqualTo(0);
        Assertions.assertThat(res2).isEqualTo(1);
        Assertions.assertThat(res3).isEqualTo(2);
    }
}