package reservation.Service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
    @Mock ApplicationEventPublisher applicationEventPublisher;

    public PaymentServiceTest() {
        jpaReservationCoreRepository = Mockito.mock(JpaReservationCoreRepository.class);
        jpaPaymentCoreRepository = Mockito.mock(JpaPaymentCoreRepository.class);
        jpaAccountCoreRepository = Mockito.mock(JpaAccountCoreRepository.class);
        jpaUserCoreRepository = Mockito.mock(JpaUserCoreRepository.class);
        jpaAccountHistoryCoreRepository = Mockito.mock(JpaAccountHistoryCoreRepository.class);
        applicationEventPublisher = Mockito.mock(ApplicationEventPublisher.class);

        paymentService = new PaymentService(
                jpaReservationCoreRepository,
                jpaPaymentCoreRepository,
                jpaAccountCoreRepository,
                jpaUserCoreRepository,
                jpaAccountHistoryCoreRepository,
                applicationEventPublisher
        );
    }

    @Test
    public void test_예약된_좌석_결제하기() throws Exception{
        // given
        Seat seat = new Seat();
        seat.setId(1);
        Reservation reservation = Reservation.builder().seat(seat).build();
        Mockito.doReturn(reservation)
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

        Long seatId  = paymentService.payForPreReservedSeat(1, 1L);
        Assertions.assertThat(seatId).isEqualTo(1L);
    }
}