package reservation.DTO.Exception;

public class TokenUnavailableException extends ResponseException {
    private final String message = "유효하지 않은 토큰입니다.";
}
