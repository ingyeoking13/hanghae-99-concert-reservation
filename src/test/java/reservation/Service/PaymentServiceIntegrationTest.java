package reservation.Service;

import org.junit.jupiter.api.Assertions;
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
import reservation.Domain.Seat;
import reservation.Repository.JpaSeatInfoCoreRepository;

@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
public class PaymentServiceIntegrationTest {
  @Autowired PaymentService paymentService;
  @Autowired ReservationService reservationService;
  @Autowired JpaSeatInfoCoreRepository jpaSeatInfoCoreRepository;

  @Container
  static GenericContainer mySQLContainer = new MySQLContainer("mysql:8.0").withReuse(true);
  @Container
  static GenericContainer redisContainer = new GenericContainer(DockerImageName.parse("redis:7.0.8-alpine")).withExposedPorts(6379);

  @DynamicPropertySource
  static void registerPgProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.redis.host", () -> redisContainer.getHost());
    registry.add("spring.redis.port", () -> String.valueOf(redisContainer.getMappedPort(6379)));
  }


  @Test
  void test_이벤트발행테스트() {
    // given - when
    Assertions.assertDoesNotThrow(() -> reservationService.reserveSeat(51L, 1L));
    Long seatId = paymentService.payForPreReservedSeat(1, 1L);

    // then
    Seat seat = jpaSeatInfoCoreRepository.findById(seatId);
    org.assertj.core.api.Assertions.assertThat(
        seat.getOccupiedStatus()
    ).isEqualTo("Confirmed");
  }

  @Test
  void test_이벤트발행_롤백테스트() {
    // given - when
    Assertions.assertDoesNotThrow(() -> reservationService.reserveSeat(52L, 2L));
    Long seatId = paymentService.payForPreReservedSeat(1, 2L);
    System.out.println(seatId);
    // try twice
    paymentService.payForPreReservedSeat(1, 2L);

    // then
    Seat seat = jpaSeatInfoCoreRepository.findById(seatId);
    org.assertj.core.api.Assertions.assertThat(
        seat.getOccupiedStatus()
    ).isEqualTo("Reserved");
  }
}
