package reservation.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
public class ReservationSeatIntegrationTest {
  @Container
  static GenericContainer mySQLContainer = new MySQLContainer("mysql:8.0").withReuse(true);
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
          reservationService.reserveSeat(1L, 1L);
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