package reservation.Service.Event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class PaymentEvent {
  private Long userId;
  private int amount;
  public PaymentEvent(Long userId, int amount) {
    this.userId = userId;
    this.amount = amount;
  }
}
