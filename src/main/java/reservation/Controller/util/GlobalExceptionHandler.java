package reservation.Controller.util;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import reservation.DTO.BaseResponse;
import reservation.DTO.Exception.ResponseException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<BaseResponse> handleAllExceptions(
            ResponseException ex,
            WebRequest request) {

        BaseResponse response = BaseResponse.builder()
                .path(request.getContextPath())
                .result(ex.getRejectedValues())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(response, ex.getStatus());
    }
}