package reservation.Service;

import jakarta.persistence.LockModeType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reservation.Controller.util.TokenManager;
import reservation.DTO.Exception.AlreadyPaidException;
import reservation.Domain.Account;
import reservation.Domain.Reservation;
import reservation.Domain.User;
import reservation.Repository.*;
import reservation.Service.Event.PaymentEvent;
import reservation.aop.DistributedLock;

@Service
@Transactional
@AllArgsConstructor
public class PaymentService {
    @Autowired JpaReservationCoreRepository jpaReservationCoreRepository;
    @Autowired JpaPaymentCoreRepository jpaPaymentCoreRepository;
    @Autowired JpaAccountCoreRepository jpaAccountCoreRepository;
    @Autowired JpaUserCoreRepository jpaUserCoreRepository;
    @Autowired JpaAccountHistoryCoreRepository jpaAccountHistoryCoreRepository;

    @Autowired ApplicationEventPublisher eventPublisher;

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Long payForPreReservedSeat(int amount, Long userId){
        Reservation reservation = jpaReservationCoreRepository.savePaymentReservationInfo( userId );
        try{
            jpaAccountCoreRepository.findByUserId(userId);
        } catch (Exception e) {
            User user = jpaUserCoreRepository.getById(userId);
            jpaAccountCoreRepository.createUserAccount(user);
        }

        eventPublisher.publishEvent(new PaymentEvent(userId, amount));
        return reservation.getSeat().getId();
    }

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public boolean rollbackPayForPreReservedSeat(Long userId){
        jpaReservationCoreRepository.rollbackSavePaymentReservationInfo( userId );
        return true;
    }

    @DistributedLock(key="#userId")
    public Long decreaseUserAccount(int amount, Long userId) throws Exception {

        Reservation reservation = jpaReservationCoreRepository.getUserReservationInfo(userId);

        User user = jpaUserCoreRepository.getById(userId);
        try {
            jpaPaymentCoreRepository.insertPaymentInfo(
                user, reservation, amount
            );
        } catch (Exception e) {
          throw new AlreadyPaidException();
        }

        Account account = jpaAccountCoreRepository.findByUserId(userId);
        jpaAccountHistoryCoreRepository.createPaymentAccountHistory(account, amount, "Payment");
        jpaAccountCoreRepository.decreaseAmountFromAccount(user.getId(), amount);
        return reservation.getSeat().getId();
    }
}
