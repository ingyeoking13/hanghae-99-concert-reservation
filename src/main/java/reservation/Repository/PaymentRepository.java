package reservation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reservation.Domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
