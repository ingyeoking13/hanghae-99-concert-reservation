package reservation.Service.Event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import reservation.DTO.Exception.AlreadyPaidException;
import reservation.Service.PaymentService;

@Slf4j
@Component
public class PaymentEventListener {

  @Autowired PaymentService paymentService;

  @Async
  @TransactionalEventListener
  public void paymentEvent(PaymentEvent event) throws Exception{
    Long userId = event.getUserId();
    int amount = event.getAmount();
    try {
      paymentService.decreaseUserAccount(amount, userId);
    } catch (Exception e) {
      log.info("Exception occur, rollback done.");
      paymentService.rollbackPayForPreReservedSeat(userId);
    }
  }
}
