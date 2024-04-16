package reservation.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reservation.Domain.Payment;
import reservation.Domain.Reservation;
import reservation.Domain.User;

@Repository
public class JpaPaymentCoreRepository {
    @Autowired private PaymentRepository paymentRepository;
    public Payment insertPaymentInfo(User user, Reservation reservation, int amount){
        Payment payment = Payment.builder()
                .reservation(reservation)
                .user(user)
                .amount(amount)
                .build();
        paymentRepository.save(payment);
        return payment;
    }
}
