package reservation.Repository.ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Order;
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

    @AfterEach
    void cleanTicketWriter(){
        ticketWriterRepository.cleanTicketWriter();
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

    @Test
    void test_대기표_읽은후_삭제확인_동시성테스트() {

        ticketWriterRepository.writeNewTicket("test", 1);
        int numThreads = 60;

        ExecutorService executorService = Executors.newFixedThreadPool(60);
        List<CompletableFuture> futures = new ArrayList<>();

        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger failCount = new AtomicInteger(0);

        for (int i=1; i<= numThreads; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                Long res = ticketReaderRepository.readAndDeleteWaitingNumber("test-1");
                System.out.println(res);
                if (res == 0) successCount.incrementAndGet();
                else failCount.incrementAndGet();
            }, executorService);
            futures.add(future);
        }

        CompletableFuture.allOf((futures.toArray(new CompletableFuture[0]))).join();
        executorService.shutdown();

        Assertions.assertThat(successCount.get()).isEqualTo(1);
        Assertions.assertThat(failCount.get()).isEqualTo(59);
    }
}