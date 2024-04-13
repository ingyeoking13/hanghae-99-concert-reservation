package reservation.DTO.Exception;

import lombok.*;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseException extends Exception {
    private HttpStatusCode status;
    private String message;
    private Object rejectedValues;
}
