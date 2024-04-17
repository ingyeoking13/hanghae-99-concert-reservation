package reservation.DTO.Exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlreadyPaidException extends ResponseException {
    private String message = "이미 결제된 내역입니다.";
}