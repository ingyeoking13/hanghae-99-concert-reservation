package reservation.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import reservation.DTO.Request.Payment;
import reservation.Service.PaymentService;

@Controller
public class PaymentController {
    @Autowired PaymentService paymentService;
    @PostMapping("/payment")
    public boolean postPayment(
            @RequestHeader("token_id") String tokenId,
            @RequestBody Payment payment) {
        paymentService.payForPreReservedSeat(payment.getAmount(), tokenId);
        return true;
    }

}
