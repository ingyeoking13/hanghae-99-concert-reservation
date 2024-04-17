package reservation.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reservation.Controller.util.TokenManager;
import reservation.DTO.Exception.AlreadyPaidException;
import reservation.Domain.Account;
import reservation.Domain.Reservation;
import reservation.Domain.User;
import reservation.Repository.*;

@Service
@Transactional
public class PaymentService {
    @Autowired JpaReservationCoreRepository jpaReservationCoreRepository;
    @Autowired JpaPaymentCoreRepository jpaPaymentCoreRepository;
    @Autowired JpaAccountCoreRepository jpaAccountCoreRepository;
    @Autowired JpaUserCoreRepository jpaUserCoreRepository;
    @Autowired JpaAccountHistoryCoreRepository jpaAccountHistoryCoreRepository;
    public boolean payForPreReservedSeat(int amount, String tokenId) throws Exception {
        TokenManager tokenManager = new TokenManager();
        Long userId = Long.valueOf(tokenManager.getUserId(tokenId));
        Reservation reservation = jpaReservationCoreRepository.savePaymentReservationInfo( userId );
        try{
            jpaAccountCoreRepository.findByUserId(userId);
        } catch (Exception e) {
            User user = jpaUserCoreRepository.getById(userId);
            jpaAccountCoreRepository.createUserAccount(user);
        }
        try {
            jpaPaymentCoreRepository.insertPaymentInfo(
                    reservation.getUser(), reservation, amount
            );
        } catch (Exception e) {
            throw new AlreadyPaidException();

        }

        Account account = jpaAccountCoreRepository.findByUserId(userId);
        jpaAccountHistoryCoreRepository.createPaymentAccountHistory(account, amount, "Payment");
        jpaAccountCoreRepository.decreaseAmountFromAccount(account.getUser().getId(), amount);
        return true;
    }
}
