package reservation.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
public class ReservationSeatIntegrationTest {
  @Container
  static GenericContainer mySQLContainer = new MySQLContainer("mysql:8.0").withReuse(true);
  @Container
  static GenericContainer redisContainer = new GenericContainer(DockerImageName.parse("redis:7.0.8-alpine")).withExposedPorts(6379);

  @DynamicPropertySource
  static void registerPgProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.redis.host", () -> redisContainer.getHost());
    registry.add("spring.redis.port", () -> String.valueOf(redisContainer.getMappedPort(6379)));
  }


  @Autowired ReservationService reservationService;

  @Test
  void test_좌석동시예약(){
    int numThreads = 60;

    ExecutorService executorService = Executors.newFixedThreadPool(60);
    List<CompletableFuture<Void>> futures = new ArrayList<>();


    AtomicInteger successCount = new AtomicInteger(0);
    AtomicInteger failCount = new AtomicInteger(0);

    for (int i = 1; i <= numThreads; i++) {
      CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
        try {
          reservationService.reserveSeat(51L, 1L);
          successCount.incrementAndGet();
        } catch (Exception e) {
          System.out.println(e);
          failCount.incrementAndGet();
        }
      }, executorService);
      futures.add(future);
    }

    CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    executorService.shutdown();

    Assertions.assertThat(successCount.get()).isEqualTo(1);
    Assertions.assertThat(failCount.get()).isEqualTo(59);
  }

}
