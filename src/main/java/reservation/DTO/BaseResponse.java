package reservation.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {
    private Integer statusCode;
    private String message;
    private T result;

    public BaseResponse(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public BaseResponse(Integer statusCode, String message, T result) {
        this.statusCode = statusCode;
        this.message = message;
        this.result = result;
    }

}