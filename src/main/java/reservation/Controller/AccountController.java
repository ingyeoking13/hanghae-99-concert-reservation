package reservation.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reservation.DTO.Request.Charge;
import reservation.Service.AccountService;

@RestController
public class AccountController {
    @Autowired AccountService accountService;

    @GetMapping("/amount")
    public int getAmount(@RequestHeader("Authorization") Long userId) {
        return accountService.getAmount(userId);
    }

    @PostMapping("/charge")
    public boolean charge(@RequestHeader("Authorization") Long userId, @RequestBody Charge charge) {
        return accountService.charge(userId, charge.getAmount());
    }

}
