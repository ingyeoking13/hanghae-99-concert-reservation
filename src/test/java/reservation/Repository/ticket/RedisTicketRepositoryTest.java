package reservation.Repository.ticket;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@ActiveProfiles("test")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
class RedisTicketRepositoryTest {

    GenericContainer redisContainer;

    @Autowired private TicketReaderRepository ticketReaderRepository;
    @Autowired private TicketWriterRepository ticketWriterRepository;

    @BeforeEach
    public void beforeEach() {
        redisContainer = new GenericContainer(DockerImageName.parse("redis:7.0.8-alpine")).withExposedPorts(6379);
        redisContainer.start();
        System.setProperty("spring.data.redis.host", redisContainer.getHost());
        System.setProperty("spring.data.redis.port", String.valueOf(redisContainer.getMappedPort(6379)));
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
        Assertions.assertThat(res1).isEqualTo(1);
        Assertions.assertThat(res2).isEqualTo(2);
        Assertions.assertThat(res3).isEqualTo(3);
    }
}