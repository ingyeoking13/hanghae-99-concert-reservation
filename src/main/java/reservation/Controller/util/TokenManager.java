package reservation.Controller.util;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TokenManager {

    public String getUserId(String token) {
        String[] tokens = token.split("-");
        return tokens[1];
    }
}
