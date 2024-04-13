package reservation.DTO.Exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenUnavailableException extends ResponseException {
    private String message = "유효하지 않은 토큰입니다.";
}
