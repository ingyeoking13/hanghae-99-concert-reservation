package reservation.DTO.Exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlreadyOccupiedException extends ResponseException {
    private String message = "이미 차지 된 좌석입니다.";
}

