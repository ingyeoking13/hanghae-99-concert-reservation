package reservation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reservation.Domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
