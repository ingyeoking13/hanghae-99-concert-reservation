package reservation.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reservation.Controller.util.TokenManager;
import reservation.DTO.Request.Payment;
import reservation.Service.PaymentService;

@RestController
public class PaymentController {
    @Autowired PaymentService paymentService;
    @PostMapping("/payment")
    public boolean postPayment(
            @RequestHeader("token_id") String tokenId,
            @RequestBody Payment payment) throws Exception {
        TokenManager tm = new TokenManager();
        Long userId = Long.valueOf(tm.getUserId(tokenId));
        paymentService.payForPreReservedSeat(payment.getAmount(), userId);
        return true;
    }

}
