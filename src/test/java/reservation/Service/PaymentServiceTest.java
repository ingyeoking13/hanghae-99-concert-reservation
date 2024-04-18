package reservation.Service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import reservation.Domain.*;
import reservation.Repository.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;

class PaymentServiceTest {
    PaymentService paymentService;
    @Mock JpaReservationCoreRepository jpaReservationCoreRepository;
    @Mock JpaPaymentCoreRepository jpaPaymentCoreRepository;
    @Mock JpaAccountCoreRepository jpaAccountCoreRepository;
    @Mock JpaUserCoreRepository jpaUserCoreRepository;
    @Mock JpaAccountHistoryCoreRepository jpaAccountHistoryCoreRepository;

    public PaymentServiceTest() {
        jpaReservationCoreRepository = Mockito.mock(JpaReservationCoreRepository.class);
        jpaPaymentCoreRepository = Mockito.mock(JpaPaymentCoreRepository.class);
        jpaAccountCoreRepository = Mockito.mock(JpaAccountCoreRepository.class);
        jpaUserCoreRepository = Mockito.mock(JpaUserCoreRepository.class);
        jpaAccountHistoryCoreRepository = Mockito.mock(JpaAccountHistoryCoreRepository.class);

        paymentService = new PaymentService(
                jpaReservationCoreRepository,
                jpaPaymentCoreRepository,
                jpaAccountCoreRepository,
                jpaUserCoreRepository,
                jpaAccountHistoryCoreRepository
        );
    }

    @Test
    public void test_예약된_좌석_결제하기() throws Exception{
        // given
        Mockito.doReturn(mock(Reservation.class))
                .when(jpaReservationCoreRepository).savePaymentReservationInfo(1L);
        Mockito.doReturn(mock(Account.class))
                .when(jpaAccountCoreRepository).findByUserId(1L);
        Mockito.doReturn(new Payment()).when(jpaPaymentCoreRepository).insertPaymentInfo(
                any(User.class),
                any(Reservation.class),
                anyInt()
        );
        Mockito.doReturn(mock(Account.class)).when(jpaAccountCoreRepository).findByUserId(1L);
        Mockito.doReturn(new AccountHistory()).when(jpaAccountHistoryCoreRepository).createPaymentAccountHistory(
                any(Account.class),
                anyInt(),
                anyString()
        );
        Mockito.doReturn(mock(User.class)).when(jpaUserCoreRepository).getById(anyLong());
        Mockito.doReturn(new Account()).when(jpaAccountCoreRepository).decreaseAmountFromAccount(
                anyLong(),
                anyInt()
        );

        boolean result = paymentService.payForPreReservedSeat(1, 1L);
        Assertions.assertThat(result).isEqualTo(true);
    }
}