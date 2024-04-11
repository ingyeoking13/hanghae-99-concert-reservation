package reservation.DTO.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseException extends Exception {
    private HttpStatusCode status;
    private String message;
    private Object rejectedValues;
}
