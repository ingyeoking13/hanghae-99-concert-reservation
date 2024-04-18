package reservation.Controller.util;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import reservation.DTO.BaseResponse;
import reservation.DTO.Exception.ResponseException;

import java.util.LinkedHashMap;

@RestControllerAdvice
public class ResponseWrapper implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return MappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if (body instanceof LinkedHashMap) {
            LinkedHashMap<?, ?> errorResponse = (LinkedHashMap<?, ?>) body;
            return BaseResponse.builder()
                    .path((String) errorResponse.get("path"))
                    .result("")
                    .message((String) errorResponse.get("message"))
                    .build();
        }

        if (body instanceof BaseResponse) {
            return body;
        }

        return BaseResponse.builder()
                .result(body)
                .build();
    }
}
