package reservation.Controller.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import reservation.Service.TicketService;

@Slf4j
@Component
public class TicketValidatorInterceptor implements HandlerInterceptor {

    @Autowired
    private TicketService ticketService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String tokenId = request.getHeader("token_id");
        ticketService.poolingWaitingQueue(tokenId);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
