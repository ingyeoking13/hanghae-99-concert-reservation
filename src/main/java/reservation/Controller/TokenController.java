package reservation.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import reservation.DTO.Token;

@RestController
public class TokenController {

    @PostMapping("/token")
    public Token makeToken() {
        return Token
                .builder()
                .token("token")
                .build();
    }
}
