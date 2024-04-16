package reservation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reservation.Domain.AccountHistory;

public interface AccountHistoryRepository extends JpaRepository<AccountHistory, Long> {
}
