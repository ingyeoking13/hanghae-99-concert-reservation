package reservation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reservation.Domain.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    public Optional<Account> findByUser_Id(Long id);
}
