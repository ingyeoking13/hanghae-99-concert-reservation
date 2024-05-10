package reservation.Repository;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
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

@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
public class JpaSeatInfoIntegrationTest {
  @Container
  static GenericContainer mySQLContainer = new MySQLContainer("mysql:8.0").withReuse(true);
  @Container
  static GenericContainer redisContainer = new GenericContainer(DockerImageName.parse("redis:7.0.8-alpine")).withExposedPorts(6379);

  @Autowired
  private JpaSeatInfoCoreRepository jpaSeatInfoCoreRepository;

  @DynamicPropertySource
  static void registerPgProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.redis.host", () -> redisContainer.getHost());
    registry.add("spring.redis.port", () -> String.valueOf(redisContainer.getMappedPort(6379)));
  }

  @Test
  void test_좌석정보가져오기() {

    List<Seat> seats = jpaSeatInfoCoreRepository.getAllSeats(1L);
    Assertions.assertThat(seats.size()).isGreaterThan(0);
  }

}
