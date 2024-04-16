package reservation.Controller.util;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TokenManager {

    public String getUserId(String token) {
        String[] tokens = token.split("-");
        return tokens[1];
    }
}
